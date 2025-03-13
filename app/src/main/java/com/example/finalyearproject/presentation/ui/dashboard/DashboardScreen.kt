package com.example.finalyearproject.presentation.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalyearproject.presentation.viewmodel.DashboardViewModel
import com.example.finalyearproject.presentation.ui.timetable.TimetableScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel = viewModel()) {
    val username by remember { mutableStateOf(dashboardViewModel.getUsername()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome, $username!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /* Navigate to timetable */ }) {
                Text("View Timetable")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /* Navigate to profile */ }) {
                Text("Go to Profile")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { /* Navigate to settings */ }) {
                Text("Settings")
            }
        }
    }
}
