/*package com.example.finalyearproject

import android.content.Context
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.finalyearproject.data.excel.ExcelParser
import com.example.finalyearproject.data.local.TimetableDatabase
import com.example.finalyearproject.data.local.dao.AssignmentDao
import com.example.finalyearproject.data.local.dao.TimetableDao
import com.example.finalyearproject.data.local.entities.AssignmentEntity
import com.example.finalyearproject.data.local.entities.TimetableEntity
import com.example.finalyearproject.data.repository.TimetableRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.ByteArrayInputStream
import java.io.InputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook  // For Excel XLSX file creation
import java.io.ByteArrayOutputStream  // For writing Excel data to memory

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DataLayerTest {
    private lateinit var db: TimetableDatabase
    private lateinit var timetableDao: TimetableDao
    private lateinit var assignmentDao: AssignmentDao
    private lateinit var timetableRepository: TimetableRepositoryImpl
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, TimetableDatabase::class.java)
            .allowMainThreadQueries() // ✅ Ensures no threading issues in tests
            .build()

        timetableDao = db.timetableDao()
        assignmentDao = db.assignmentDao()
        timetableRepository = TimetableRepositoryImpl(timetableDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `test inserting and retrieving timetable entry`() = runBlocking {
        val testEntry = TimetableEntity(courseName = "Math", startTime = "08:00 AM", endTime = "09:00 AM", dayOfWeek = "Monday", venue = "Room 101")

        timetableDao.insertTimetable(listOf(testEntry))
        val result = timetableDao.getTimetable().first()

        assertEquals(1, result.size) // ✅ Ensure entry is saved
    }

    @Test
    fun `test inserting and retrieving assignments`() = runBlocking {
        val testAssignment = AssignmentEntity(
            id = 1,  // ✅ Ensure id is an Int
            courseCode = "COSC 101",
            title = "Lab Report",
            dueDate = "2024-05-01",
            notes = "Write report",
            isCompleted = false
        )


        assignmentDao.insertAssignment(testAssignment)
        val result = assignmentDao.getAssignments().first()

        assertEquals(1, result.size) // ✅ Ensure entry is saved
    }

    @Test
    fun `test Excel file parsing`() = runBlocking {
        withContext(Dispatchers.IO) {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Sheet1")

            // ✅ Create a header row
            val headerRow = sheet.createRow(0)
            headerRow.createCell(0).setCellValue("Course Name")
            headerRow.createCell(1).setCellValue("Level")
            headerRow.createCell(2).setCellValue("Start Time")
            headerRow.createCell(3).setCellValue("End Time")
            headerRow.createCell(4).setCellValue("Day")
            headerRow.createCell(5).setCellValue("Venue")

            // ✅ Add a data row
            val dataRow = sheet.createRow(1)
            dataRow.createCell(0).setCellValue("Computer Science")
            dataRow.createCell(1).setCellValue("400 Level")
            dataRow.createCell(2).setCellValue("08:00 AM")
            dataRow.createCell(3).setCellValue("10:00 AM")
            dataRow.createCell(4).setCellValue("Monday")
            dataRow.createCell(5).setCellValue("Room 101")

            // ✅ Write the workbook to a ByteArrayOutputStream
            val outputStream = ByteArrayOutputStream()
            workbook.write(outputStream)
            workbook.close()
            val byteArray = outputStream.toByteArray()

            // ✅ Convert to InputStream
            val inputStream = ByteArrayInputStream(byteArray)

            // 🔥 Call the Parser
            val result = ExcelParser.parseExcelFile(inputStream, "Computer Science", "400 Level")

            // ✅ Ensure Data is Extracted
            assert(result.isNotEmpty()) { "Excel parsing failed: No data found" }

            // ✅ Check for a specific time slot entry
            val expectedTimeSlot = "08:00 AM - 10:00 AM"
            assert(result.containsKey(expectedTimeSlot)) { "Expected time slot not found" }

            // ✅ Check if Monday has the correct entry
            assert(result[expectedTimeSlot]?.get("Monday") == "Computer Science @ Room 101") {
                "Parsed data mismatch"
            }
        }
    }
}
*/