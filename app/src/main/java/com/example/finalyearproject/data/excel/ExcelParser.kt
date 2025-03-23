package com.example.finalyearproject.data.excel

import android.util.Log
import com.example.finalyearproject.domain.model.TimetableEntry
import java.io.InputStream
import org.apache.poi.ss.usermodel.*

object ExcelParser {
    fun parseExcelFile(inputStream: InputStream, filterCourse: String, filterLevel: String): List<TimetableEntry> {
        val timetableList = mutableListOf<TimetableEntry>()

        try {
            val workbook = WorkbookFactory.create(inputStream)

            // Parse all sheets (days) in the workbook
            for (sheetIndex in 0 until workbook.numberOfSheets) {
                val sheet = workbook.getSheetAt(sheetIndex)
                val day = getDayFromSheetName(sheet.sheetName)

                Log.d("ExcelParser", "Processing sheet: ${sheet.sheetName} (day: $day)")

                // Find the row with time slots (typically has values like "7-7:50", "8-8:50", etc.)
                val timeRow = findTimeRow(sheet)
                if (timeRow == -1) {
                    Log.e("ExcelParser", "Could not find time row in sheet: ${sheet.sheetName}")
                    continue
                }

                // Extract time slots
                val timeSlots = extractTimeSlots(sheet, timeRow)
                Log.d("ExcelParser", "Found time slots: $timeSlots")

                // Find the venue column (typically first column)
                val venueCol = findVenueColumn(sheet)
                if (venueCol == -1) {
                    Log.e("ExcelParser", "Could not find venue column in sheet: ${sheet.sheetName}")
                    continue
                }

                // Process each row (venue + classes)
                processRows(sheet, timeRow, venueCol, timeSlots, day, filterCourse, filterLevel, timetableList)
            }

            workbook.close()

            Log.d("ExcelParser", "Total timetable entries: ${timetableList.size}")

        } catch (e: Exception) {
            Log.e("ExcelParser", "❌ Error parsing Excel: ${e.message}", e)
        } finally {
            inputStream.close()
        }

        return timetableList
    }

    private fun getDayFromSheetName(sheetName: String): String {
        return when {
            sheetName.contains("monday", ignoreCase = true) -> "Monday"
            sheetName.contains("tuesday", ignoreCase = true) -> "Tuesday"
            sheetName.contains("wednesday", ignoreCase = true) -> "Wednesday"
            sheetName.contains("thursday", ignoreCase = true) -> "Thursday"
            sheetName.contains("friday", ignoreCase = true) -> "Friday"
            sheetName.contains("saturday", ignoreCase = true) -> "Saturday"
            sheetName.contains("sunday", ignoreCase = true) -> "Sunday"
            else -> {
                // Extract day from title if present (like "TIMETABLE (TUESDAY)")
                when {
                    sheetName.contains("monday", ignoreCase = true) -> "Monday"
                    sheetName.contains("tuesday", ignoreCase = true) -> "Tuesday"
                    sheetName.contains("wednesday", ignoreCase = true) -> "Wednesday"
                    sheetName.contains("thursday", ignoreCase = true) -> "Thursday"
                    sheetName.contains("friday", ignoreCase = true) -> "Friday"
                    sheetName.contains("saturday", ignoreCase = true) -> "Saturday"
                    sheetName.contains("sunday", ignoreCase = true) -> "Sunday"
                    else -> sheetName // Use sheet name if day can't be determined
                }
            }
        }
    }

    private fun findTimeRow(sheet: Sheet): Int {
        // Look for a row that has multiple time-formatted cells
        for (rowIndex in 0..20) { // Check first 20 rows
            val row = sheet.getRow(rowIndex) ?: continue
            var timeCount = 0

            for (cellIndex in 0 until row.physicalNumberOfCells) {
                val cellValue = getCellValueAsString(row, cellIndex)
                // Count cells that look like time slots (contain "-" and numbers)
                if (cellValue.contains("-") && cellValue.any { it.isDigit() }) {
                    timeCount++
                }
            }

            // If multiple time slots found, this is likely the time row
            if (timeCount >= 3) {
                return rowIndex
            }
        }

        return -1
    }

    private fun findVenueColumn(sheet: Sheet): Int {
        // Look for a cell containing "VENUE" or similar text
        for (rowIndex in 0..20) {
            val row = sheet.getRow(rowIndex) ?: continue

            for (cellIndex in 0 until row.physicalNumberOfCells) {
                val cellValue = getCellValueAsString(row, cellIndex)
                if (cellValue.contains("VENUE", ignoreCase = true) ||
                    cellValue.contains("LOCATION", ignoreCase = true)) {
                    return cellIndex
                }
            }
        }

        // If not found, assume it's the first column
        return 0
    }

    private fun extractTimeSlots(sheet: Sheet, timeRow: Int): Map<Int, Pair<String, String>> {
        val timeSlots = mutableMapOf<Int, Pair<String, String>>()
        val row = sheet.getRow(timeRow) ?: return timeSlots

        for (cellIndex in 0 until row.physicalNumberOfCells) {
            val cellValue = getCellValueAsString(row, cellIndex)

            // Parse time slots like "7-7:50", "8-8:50", etc.
            if (cellValue.contains("-") && cellValue.any { it.isDigit() }) {
                val times = cellValue.split("-")
                if (times.size == 2) {
                    val startTime = normalizeTimeFormat(times[0].trim())
                    val endTime = normalizeTimeFormat(times[1].trim())
                    timeSlots[cellIndex] = Pair(startTime, endTime)
                }
            }
        }

        return timeSlots
    }

    private fun normalizeTimeFormat(time: String): String {
        // Ensure consistent time format (e.g., "7" becomes "7:00")
        return if (time.contains(":")) time else "$time:00"
    }

    private fun processRows(
        sheet: Sheet,
        timeRow: Int,
        venueCol: Int,
        timeSlots: Map<Int, Pair<String, String>>,
        day: String,
        filterCourse: String,
        filterLevel: String,
        timetableList: MutableList<TimetableEntry>
    ) {
        var currentVenue = ""
        var currentLevel = ""

        // Process rows after the time row
        for (rowIndex in (timeRow + 1) until sheet.physicalNumberOfRows) {
            val row = sheet.getRow(rowIndex) ?: continue

            // Get venue from venue column
            val venueCellValue = getCellValueAsString(row, venueCol)
            if (venueCellValue.isNotEmpty() && !venueCellValue.contains("Level", ignoreCase = true)) {
                currentVenue = venueCellValue
            }

            // Try to find level information in the row
            for (cellIndex in 0 until row.physicalNumberOfCells) {
                val cellValue = getCellValueAsString(row, cellIndex)
                if (cellValue.contains("Level", ignoreCase = true) ||
                    cellValue.matches(Regex("\\d{3}Level", RegexOption.IGNORE_CASE))) {
                    currentLevel = cellValue
                    break
                }
            }

            // Process each cell that might contain course information
            for (cellIndex in 0 until row.physicalNumberOfCells) {
                if (!timeSlots.containsKey(cellIndex)) continue

                val cellValue = getCellValueAsString(row, cellIndex)
                if (cellValue.isEmpty() || cellValue.contains("Level", ignoreCase = true)) continue

                // Extract course information
                val courseInfo = extractCourseInfo(cellValue)
                if (courseInfo.courseName.isNotEmpty()) {
                    // Apply filters
                    if ((filterCourse.isEmpty() ||
                                courseInfo.courseName.contains(filterCourse, ignoreCase = true)) &&
                        (filterLevel.isEmpty() ||
                                currentLevel.contains(filterLevel, ignoreCase = true))) {

                        val (startTime, endTime) = timeSlots[cellIndex] ?: Pair("", "")

                        timetableList.add(
                            TimetableEntry(
                                courseName = courseInfo.courseName,
                                startTime = startTime,
                                endTime = endTime,
                                day = day,
                                venue = currentVenue,
                                instructor = courseInfo.instructor
                            )
                        )

                        Log.d("ExcelParser", "Added entry: ${courseInfo.courseName} on $day at $startTime-$endTime in $currentVenue")
                    }
                }
            }
        }
    }

    private data class CourseInfo(val courseName: String, val instructor: String)

    private fun extractCourseInfo(cellValue: String): CourseInfo {
        // Examples:
        // "SENG307 (SE A) SW Arch & Design Olofopa"
        // "ITGO303 (IT) Web Server Admin Akintunde"

        if (cellValue.isEmpty()) return CourseInfo("", "")

        // Try to extract the course code
        val courseCodeRegex = Regex("[A-Z]{3,4}\\d{3}")
        val courseCode = courseCodeRegex.find(cellValue)?.value ?: ""

        if (courseCode.isEmpty()) return CourseInfo(cellValue, "Unknown Instructor")

        // Extract the course name and instructor
        val parts = cellValue.split(Regex("\\s{2,}|\\n"))
        val courseName = cellValue.trim()

        // Try to extract instructor name (usually at the end)
        val words = cellValue.split(" ")
        val instructor = if (words.size > 1) words.last() else "Unknown Instructor"

        return CourseInfo(courseName, instructor)
    }

    // Helper function to get cell value as string regardless of cell type
    private fun getCellValueAsString(row: Row, cellIndex: Int): String {
        val cell = row.getCell(cellIndex) ?: return ""

        return when (cell.cellType) {
            CellType.STRING -> cell.stringCellValue.trim()
            CellType.NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    cell.dateCellValue.toString()
                } else {
                    cell.numericCellValue.toString()
                }
            }
            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            CellType.FORMULA -> {
                try {
                    cell.stringCellValue
                } catch (e: Exception) {
                    try {
                        cell.numericCellValue.toString()
                    } catch (e: Exception) {
                        cell.toString()
                    }
                }
            }
            else -> ""
        }.trim()
    }
}