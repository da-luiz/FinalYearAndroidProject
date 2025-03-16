package com.example.finalyearproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finalyearproject.navigation.AppNavigation
import com.example.finalyearproject.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val timetableViewModel: TimetableViewModel = viewModel()
            val assignmentViewModel: AssignmentViewModel = viewModel()
            val notificationViewModel: NotificationViewModel = viewModel()

            AppNavigation(navController)
        }
    }
}
