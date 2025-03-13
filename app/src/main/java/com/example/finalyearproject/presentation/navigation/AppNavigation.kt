package com.example.finalyearproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.finalyearproject.presentation.ui.auth.UsernameScreen
import com.example.finalyearproject.presentation.ui.auth.WelcomeScreen
import com.example.finalyearproject.presentation.ui.dashboard.DashboardScreen
import com.example.finalyearproject.presentation.ui.timetable.AddTimetableScreen
import com.example.finalyearproject.presentation.ui.timetable.TimetableDetailsScreen
import com.example.finalyearproject.presentation.ui.timetable.TimetableScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "timetable") {
        composable("timetable") {
            TimetableScreen(navController)
        }
        composable("timetable_details/{subject}/{time}") { backStackEntry ->
            TimetableDetailsScreen(
                navController,
                backStackEntry.arguments?.getString("subject"),
                backStackEntry.arguments?.getString("time")
            )
        }
        composable("add_timetable") {
            AddTimetableScreen(navController)
        }
    }

}
