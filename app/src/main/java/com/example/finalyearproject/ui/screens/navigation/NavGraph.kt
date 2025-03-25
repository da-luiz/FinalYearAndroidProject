package com.example.finalyearproject.ui.screens.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.ui.screens.HomeScreen
import com.example.finalyearproject.ui.screens.fileimport.FileImportScreen
import com.example.finalyearproject.ui.screens.login.LoginScreen
import com.example.finalyearproject.ui.screens.more.MoreScreen
import com.example.finalyearproject.ui.screens.notifications.NotificationsScreen
import com.example.finalyearproject.ui.screens.timetable.TimetableScreen
import com.example.finalyearproject.ui.screens.assignments.AssignmentTrackerScreen
import com.example.finalyearproject.ui.screens.profile.ProfileScreen
import com.example.finalyearproject.ui.screens.settings.SettingsScreen
import com.example.finalyearproject.viewmodel.TimetableViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    showBottomBar: MutableState<Boolean>,
    viewModel: TimetableViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "login screen"
    ) {
        // ... (keep your existing login and file import screens)

        // Main bottom nav screens
        composable(Screen.Home.route) {
            showBottomBar.value = true
            HomeScreen(navController)
        }

        composable(Screen.Assignments.route) {
            showBottomBar.value = true
            AssignmentTrackerScreen(navController)
        }

        composable(Screen.Notifications.route) {
            showBottomBar.value = true
            NotificationsScreen(navController)
        }

        // More section screens
        composable(Screen.Profile.route) {
            showBottomBar.value = true
            ProfileScreen(navController)
        }

        composable(Screen.Settings.route) {
            showBottomBar.value = true
            SettingsScreen(navController)
        }

        composable(Screen.Schedule.route + "/{timetableJson}") { backStackEntry ->
            showBottomBar.value = true
            val json = backStackEntry.arguments?.getString("timetableJson") ?: ""
            val timetableData = try {
                Gson().fromJson(json, object : TypeToken<List<TimetableEntry>>() {}.type)
            } catch (e: Exception) {
                emptyList<TimetableEntry>()
            }
            TimetableScreen(timetableData)
        }

        // More screen could show a menu to access Profile, Settings, Schedule
        composable(Screen.More.route) {
            showBottomBar.value = true
            MoreOptionsScreen(
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) },
                onScheduleClick = {
                    navController.navigate("${Screen.Schedule.route}/${Gson().toJson(viewModel.timetableData.value)}")
                }
            )
        }
    }
}