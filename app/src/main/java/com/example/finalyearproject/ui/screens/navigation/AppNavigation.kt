package com.example.finalyearproject.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalyearproject.data.local.TimetableDatabase
import com.example.finalyearproject.data.repository.TimetableRepositoryImpl
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.ui.screens.fileimport.FileImportScreen
import com.example.finalyearproject.ui.screens.login.LoginScreen
import com.example.finalyearproject.ui.screens.timetable.TimetableScreen
import com.example.finalyearproject.viewmodel.TimetableViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.finalyearproject.ui.screens.more.MoreScreen
import com.example.finalyearproject.ui.screens.HomeScreen
import com.example.finalyearproject.ui.screens.settings.SettingsScreen
import com.example.finalyearproject.ui.screens.assignments.AssignmentTrackerScreen
import com.example.finalyearproject.ui.screens.notifications.NotificationsScreen
import com.example.finalyearproject.ui.screens.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    // Create a shared ViewModel instance that can be used across all screens
    val context = LocalContext.current
    val database = remember { TimetableDatabase.getInstance(context) }
    val repository = remember { TimetableRepositoryImpl(database.timetableDao()) }
    val viewModel = viewModel { TimetableViewModel(repository) }

    // Collect timetable data as State
    val timetableData by viewModel.timetableData.collectAsState()

    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") {
            LoginScreen { navController.navigate("fileImportScreen") }
        }

        composable("fileImportScreen") {
            FileImportScreen(navController = navController, viewModel = viewModel)
        }

        // Add routes for all screens
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Assignments.route) {
            AssignmentTrackerScreen(navController)
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(navController)
        }

        composable(Screen.More.route) {
            MoreScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }

        composable(Screen.Schedule.route + "/{timetableJson}") { backStackEntry ->
            val json = backStackEntry.arguments?.getString("timetableJson")
            val finalTimetableData = json?.let {
                try {
                    Gson().fromJson(it, object : TypeToken<List<TimetableEntry>>() {}.type)
                } catch (e: Exception) {
                    timetableData  // Use collected StateFlow data as fallback
                }
            } ?: timetableData

            TimetableScreen(finalTimetableData)
        }
    }
}