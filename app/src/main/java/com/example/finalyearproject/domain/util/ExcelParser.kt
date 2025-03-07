package com.example.finalyearproject.domain.util

import java.io.InputStream
import com.example.finalyearproject.domain.model.TimetableEntry
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.CellType

object ExcelParser {

    /**
     * Parses an Excel file and extracts only timetable entries matching Course Name & Level.
     *
     * @param inputStream The input stream of the Excel file.
     * @param filterCourse The course name to filter by (e.g., "Computer Science").
     * @param filterLevel The level to filter by (e.g., "400 Level").
     * @return List of filtered TimetableEntry objects.
     */
    fun parseExcelFile(
        inputStream: InputStream,
        filterCourse: String,
        filterLevel: String
    ): Map<String, Map<String, String>> {  //  Returns a Timetable Grid
        val timetable = mutableMapOf<String, MutableMap<String, String>>()

        try {
            val workbook = WorkbookFactory.create(inputStream)
            val sheet = workbook.getSheetAt(0)

            for (i in 1 until sheet.physicalNumberOfRows) { // Skip header row
                val row: Row = sheet.getRow(i) ?: continue
                val courseName = getCellValueAsString(row, 0)  // Column 0 = Course Name
                val level = getCellValueAsString(row, 1)       // Column 1 = Level
                val startTime = getCellValueAsString(row, 2)   // Column 2 = Start Time
                val endTime = getCellValueAsString(row, 3)     // Column 3 = End Time
                val dayOfWeek = getCellValueAsString(row, 4)   // Column 4 = Day
                val venue = getCellValueAsString(row, 5)       // Column 5 = Venue

                val matchesCourse = courseName.equals(filterCourse, ignoreCase = true)
                val matchesLevel = level.equals(filterLevel, ignoreCase = true)

                if (matchesCourse && matchesLevel) {
                    val timeSlot = "$startTime - $endTime" //  Format: "09:00 - 10:00 AM"
                    val courseDetails = "$courseName @ $venue" //  Format: "Math @ Room A"

                    //  Add to timetable map
                    if (!timetable.containsKey(timeSlot)) {
                        timetable[timeSlot] = mutableMapOf()
                    }
                    timetable[timeSlot]!![dayOfWeek] = courseDetails
                }
            }

            workbook.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return timetable  //  Now returns a grid-like structure for the timetable
    }

    private fun getCellValueAsString(row: Row, cellIndex: Int): String {
        val cell = row.getCell(cellIndex) ?: return ""  // Handle null cell case

        return when (cell.cellType) {
            CellType.STRING -> cell.stringCellValue ?: ""  // If it's a string, return it
            CellType.NUMERIC -> cell.numericCellValue.toString()  // Convert numbers to string
            CellType.BOOLEAN -> cell.booleanCellValue.toString()  // Convert boolean to string
            CellType.FORMULA -> {
                try {
                    cell.stringCellValue
                } catch (e: Exception) {
                    try {
                        cell.numericCellValue.toString()
                    } catch (e: Exception) {
                        ""
                    }
                }
            }
            else -> ""
        }
    }
}