/*package com.example.finalyearproject

import AssignmentTrackerScreen
import ProfileScreen
import SchedulesScreen
import SettingsScreen
import TimetableScreen
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.finalyearproject.ui.screens.fileimport.FileImportScreen
import com.example.finalyearproject.ui.screens.login.LoginScreen
import com.example.finalyearproject.ui.screens.notifications.NotificationsScreen
import com.example.finalyearproject.ui.screens.timetableoptions.TimetableOptionsScreen
import com.example.finalyearproject.viewmodel.TimetableViewModel
import org.junit.Rule
import org.junit.Test

class UIScreensTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        composeTestRule.setContent { LoginScreen {} }
        composeTestRule.onNodeWithText("Welcome!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Proceed").assertIsDisplayed()
    }

    @Test
    fun testFileImportScreen() {
        composeTestRule.setContent {
            FileImportScreen(viewModel = TimetableViewModel(FakeRepository())) // ✅ Pass Fake Repository
        }

        // ✅ Remove duplicate `setContent {}` call
        composeTestRule.onNodeWithText("Enter Course Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Import .xlsx File").assertIsDisplayed()
    }


    @Test
    fun testAssignmentTrackerScreen() {
        composeTestRule.setContent { AssignmentTrackerScreen() }
        composeTestRule.onNodeWithText("Assignment Tracker").assertIsDisplayed()
    }

    @Test
    fun testNotificationsScreen() {
        composeTestRule.setContent { NotificationsScreen() }
        composeTestRule.onNodeWithText("Notifications").assertIsDisplayed()
    }

    @Test
    fun testProfileScreen() {
        composeTestRule.setContent { ProfileScreen() }
        composeTestRule.onNodeWithText("Username:").assertIsDisplayed()
    }

    @Test
    fun testSchedulesScreen() {
        composeTestRule.setContent { SchedulesScreen() }
        composeTestRule.onNodeWithText("Schedules").assertIsDisplayed()
    }

    @Test
    fun testSettingsScreen() {
        composeTestRule.setContent { SettingsScreen() }
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun testTimetableScreen() {
        composeTestRule.setContent { TimetableScreen() }
        composeTestRule.onNodeWithText("Weekly Timetable").assertIsDisplayed()
    }

    @Test
    fun testTimetableOptionsScreen() {
        composeTestRule.setContent { TimetableOptionsScreen() }
        composeTestRule.onNodeWithText("Timetable Options").assertIsDisplayed()
    }
}
*/