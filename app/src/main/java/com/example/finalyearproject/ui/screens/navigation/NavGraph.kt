package com.example.finalyearproject.ui.screens.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.ui.screens.fileimport.FileImportScreen
import com.example.finalyearproject.ui.screens.login.LoginScreen
import com.example.finalyearproject.ui.screens.more.MoreScreen
import com.example.finalyearproject.ui.screens.notifications.NotificationsScreen
import com.example.finalyearproject.ui.screens.timetable.TimetableScreen
import com.example.finalyearproject.ui.screens.assignments.AssignmentTrackerScreen
import com.example.finalyearproject.viewmodel.TimetableViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    showBottomBar: MutableState<Boolean>,
    timetableData: List<TimetableEntry>,
    viewModel: TimetableViewModel // ✅ Ensure ViewModel is passed correctly
) {
    NavHost(
        navController = navController,
        startDestination = "loginScreen"
    ) {
        composable("loginScreen") {
            LoginScreen {
                navController.navigate("fileImportScreen")
            }
            showBottomBar.value = false // ✅ Hide Bottom Nav Bar on Login
        }

        composable("fileImportScreen") {
            FileImportScreen(navController = navController, viewModel = viewModel) // ✅ Pass ViewModel here
            showBottomBar.value = false // ✅ Hide Bottom Nav Bar on File Import
        }

        composable("timetableScreen/{timetableJson}") { backStackEntry ->
            val timetableJson = backStackEntry.arguments?.getString("timetableJson") ?: "[]"
            val timetableData: List<TimetableEntry> = try {
                Gson().fromJson(timetableJson, object : TypeToken<List<TimetableEntry>>() {}.type)
            } catch (e: Exception) {
                Log.e("NavGraph", "Error decoding JSON", e)
                emptyList()
            }

            Log.d("NavGraph", "✅ Decoded Timetable Data: $timetableData")

            TimetableScreen(timetableData) // ✅ Ensure correct data is passed
        }


        composable("assignments") { AssignmentTrackerScreen(navController) }
        composable("notifications") { NotificationsScreen(navController) }
        composable("more") { MoreScreen(navController) }
    }
}
