package com.example.finalyearproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finalyearproject.data.local.TimetableDatabase
import com.example.finalyearproject.data.repository.TimetableRepositoryImpl
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.ui.screens.navigation.BottomNavBar
import com.example.finalyearproject.ui.screens.navigation.SetupNavGraph
import com.example.finalyearproject.viewmodel.TimetableViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val showBottomBar = remember { mutableStateOf(false) }

            // ✅ Get database instance & repository
            val database = remember { TimetableDatabase.getInstance(this) }
            val repository = remember { TimetableRepositoryImpl(database.timetableDao()) }

            // ✅ Create ViewModel using Factory
            val viewModel: TimetableViewModel = viewModel(factory = TimetableViewModel.Factory(repository))

            // ✅ Sample Timetable Data (Replace with actual extracted data)
            val timetableData = remember {
                mutableStateOf(
                    listOf(
                        TimetableEntry("Mathematics", "8:00 AM", "9:30 AM", "Monday", "Dr. Smith", "Room 101"),
                        TimetableEntry("Physics", "10:00 AM", "11:30 AM", "Monday", "Prof. Johnson", "Lab 3")
                    )
                )
            }

            Scaffold(
                bottomBar = {
                    if (showBottomBar.value) {
                        BottomNavBar(navController)
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    SetupNavGraph(
                        navController = navController,
                        showBottomBar = showBottomBar,
                        timetableData = timetableData.value,
                        viewModel = viewModel // ✅ Pass ViewModel properly
                    )
                }
            }
        }
    }
}
