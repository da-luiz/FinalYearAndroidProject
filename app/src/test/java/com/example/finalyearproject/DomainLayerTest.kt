/*package com.example.finalyearproject

import com.example.finalyearproject.domain.model.*
import com.example.finalyearproject.domain.repository.*
import com.example.finalyearproject.domain.usecase.*
import com.example.finalyearproject.domain.usecase.Assignments.AddAssignmentUseCase
import com.example.finalyearproject.domain.usecase.Assignments.GetAssignmentsUseCase
import com.example.finalyearproject.domain.usecase.Assignments.GetOverdueAssignmentsUseCase
import com.example.finalyearproject.domain.usecase.Assignments.GetUpcommingAssignmentsUseCase
import com.example.finalyearproject.domain.usecase.Reminder.CancelReminderUseCase
import com.example.finalyearproject.domain.usecase.Reminder.ScheduleReminderUseCase
import com.example.finalyearproject.domain.usecase.Timetable.AddCustomTimetableEntryUseCase
import com.example.finalyearproject.domain.usecase.Timetable.DeleteTimetableEntryUseCase
import com.example.finalyearproject.domain.usecase.Timetable.EditTimetableEntryUseCase
import com.example.finalyearproject.domain.usecase.Timetable.GetTimetableUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlinx.coroutines.flow.first
import java.util.Date

class DomainLayerTest {
    private lateinit var timetableRepository: TimetableRepository
    private lateinit var assignmentRepository: AssignmentRepository
    private lateinit var reminderRepository: ReminderRepository
    private lateinit var scheduleRepository: ScheduleRepository
    private lateinit var updateTimetableEntry: updateTimetableEntry

    private lateinit var scheduleReminderUseCase: ScheduleReminderUseCase
    private lateinit var cancelReminderUseCase: CancelReminderUseCase
    private lateinit var addAssignmentUseCase: AddAssignmentUseCase
    private lateinit var getUpcommingAssignmentsUseCase: GetUpcommingAssignmentsUseCase
    private lateinit var getAssignmentsUseCase: GetAssignmentsUseCase
    private lateinit var getOverdueAssignmentsUseCase: GetOverdueAssignmentsUseCase
    private lateinit var getTimetableUseCase: GetTimetableUseCase
    private lateinit var getEditTimetableEntryUseCase: EditTimetableEntryUseCase
    private lateinit var getDeleteTimetableEntryUseCase: DeleteTimetableEntryUseCase
    private lateinit var getAddCustomTimetableEntryUseCase: AddCustomTimetableEntryUseCase

    @Before
    fun setUp() {
        // Mock all repositories
        timetableRepository = mock()
        assignmentRepository = mock()
        reminderRepository = mock()
        scheduleRepository = mock()
        updateTimetableEntry = mock()

        // Mock use cases
        scheduleReminderUseCase = ScheduleReminderUseCase(reminderRepository)
        cancelReminderUseCase = CancelReminderUseCase(reminderRepository)
        getUpcommingAssignmentsUseCase = GetUpcommingAssignmentsUseCase(assignmentRepository)
        getOverdueAssignmentsUseCase = GetOverdueAssignmentsUseCase(assignmentRepository)
        getAssignmentsUseCase = GetAssignmentsUseCase(assignmentRepository)
        addAssignmentUseCase = AddAssignmentUseCase(assignmentRepository)
        getTimetableUseCase = GetTimetableUseCase(timetableRepository)
        getEditTimetableEntryUseCase = EditTimetableEntryUseCase(timetableRepository)
        getDeleteTimetableEntryUseCase = DeleteTimetableEntryUseCase(timetableRepository)
        getAddCustomTimetableEntryUseCase = AddCustomTimetableEntryUseCase(timetableRepository)
    }

    @Test
    fun `test full domain workflow`() = runBlocking {
        // ✅ Mock Timetable Retrieval
        val testTimetable = listOf(
            TimetableEntry("Math", "08:00 AM", "09:00 AM", "Monday", "Room 101")
        )
        whenever(timetableRepository.getTimetable()).thenReturn(flowOf(testTimetable))

        // ✅ Mock Assignment Retrieval
        val testAssignment = Assignment("1", "COSC 201", "Project", Date(), "ER Diagram", false)
        whenever(assignmentRepository.getAssignments()).thenReturn(flowOf(listOf(testAssignment)))

        // ✅ Mock Reminder Scheduling
        val testReminder = AssignmentReminder("1", "Math Homework", Date())
        whenever(reminderRepository.scheduleReminder(testReminder)).thenReturn(Unit)

        // ✅ Mock Schedule Retrieval
        val testSchedule = Schedule("Physics", listOf(TimetableEntry("Physics", "2:00 PM", "3:00 PM", "Wednesday", "Lab 202")))
        whenever(scheduleRepository.getSchedules()).thenReturn(listOf(testSchedule))

        // ✅ Run Use Case: Get Timetable
        val timetableResult = getTimetableUseCase.execute().first()
        assertEquals(testTimetable, timetableResult)

        // ✅ Run Use Case: Add Assignment
        addAssignmentUseCase.execute(testAssignment)
        verify(assignmentRepository).addAssignment(testAssignment) // Ensure function was called
    }
}
*/