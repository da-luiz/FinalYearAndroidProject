package com.example.finalyearproject.presentation.ui.timetable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalyearproject.presentation.viewmodel.TimetableViewModel
import kotlin.collections.find

@Composable
fun TimetableDetailsScreen(
    navController: NavController,
    subject: String?,
    time: String?,
    viewModel: TimetableViewModel = viewModel()
) {
    val timetableEntries by viewModel.timetable.collectAsState(emptyList())
    val entry = timetableEntries.find { it.subject == subject && it.time == time }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Timetable Details") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Text("<") } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            entry?.let {
                Text("Subject: ${it.subject}", style = MaterialTheme.typography.headlineMedium)
                Text("Time: ${it.time}", style = MaterialTheme.typography.bodyLarge)
                Text("Location: ${it.location}", style = MaterialTheme.typography.bodyLarge)
            } ?: Text("Timetable entry not found!")
        }
    }
}
