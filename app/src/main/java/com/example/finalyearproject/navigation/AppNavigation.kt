package com.example.finalyearproject.navigation

import AssignmentTrackerScreen
import com.example.finalyearproject.ui.screens.login.LoginScreen
import com.example.finalyearproject.ui.screens.notifications.NotificationsScreen
import ProfileScreen
import SchedulesScreen
import SettingsScreen
import TimetableScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Timetable : Screen("timetable")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Schedules : Screen("schedules")
    object Assignments : Screen("assignments")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Screen.Login.route) {
        //composable(Screen.Login.route) { LoginScreen() { navController.navigate(Screen.Timetable.route) } }
        composable(Screen.Login.route) { LoginScreen() { navController.navigate(Screen.Timetable.route) }}
        composable(Screen.Timetable.route) { TimetableScreen() }
        composable(Screen.Notifications.route) { NotificationsScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
        composable(Screen.Schedules.route) { SchedulesScreen() }
        composable(Screen.Assignments.route) { AssignmentTrackerScreen() }
    }
}
