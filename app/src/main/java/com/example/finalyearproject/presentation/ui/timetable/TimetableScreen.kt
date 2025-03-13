package com.example.finalyearproject.presentation.ui.timetable
/*
import com.example.finalyearproject.presentation.components.TimetableCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalyearproject.presentation.viewmodel.TimetableViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(navController: NavController, viewModel: TimetableViewModel = viewModel()) {
    val timetableEntries: List<Timetable> by viewModel.timetable.collectAsState(emptyList())


    Scaffold(
        topBar = { TopAppBar(title = { Text("Timetable") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_timetable") }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            if (timetableEntries.isEmpty()) {
                Text("No timetable entries yet. Add one!", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn {
                    items(timetableEntries) { entry ->
                        TimetableCard(entry, onClick = {
                            // Pass subject & time to navigate to details instead of ID
                            navController.navigate("timetable_details/${entry.subject}/${entry.time}")
                        })
                    }
                }
            }
        }
    }
}
*/