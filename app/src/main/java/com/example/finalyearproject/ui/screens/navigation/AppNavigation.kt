package com.example.finalyearproject.ui.screens.navigation

import android.util.Log
import androidx.compose.runtime.Composable
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

@Composable
fun AppNavigation(navController: NavHostController) {
    // Create a shared ViewModel instance that can be used across all screens
    val context = LocalContext.current
    val database = remember { TimetableDatabase.getInstance(context) }
    val repository = remember { TimetableRepositoryImpl(database.timetableDao()) }
    val viewModel = viewModel { TimetableViewModel(repository) }

    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") {
            LoginScreen { navController.navigate("fileImportScreen") }
        }

        composable("fileImportScreen") {
            FileImportScreen(navController = navController, viewModel = viewModel)
        }

        composable("timetableScreen/{timetableJson}") { backStackEntry ->
            val timetableJson = backStackEntry.arguments?.getString("timetableJson") ?: "[]"
            val timetableData: List<TimetableEntry> = try {
                Gson().fromJson(timetableJson, object : TypeToken<List<TimetableEntry>>() {}.type)
            } catch (e: Exception) {
                Log.e("NavGraph", "Error decoding JSON", e)
                emptyList()
            }

            TimetableScreen(timetableData) // ✅ Pass timetableData instead
        }

    }
}