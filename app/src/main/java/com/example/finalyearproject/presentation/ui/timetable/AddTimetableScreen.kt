package com.example.finalyearproject.presentation.ui.timetable
/*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalyearproject.domain.models.Timetable
import com.example.finalyearproject.presentation.viewmodel.TimetableViewModel

@Composable
fun AddTimetableScreen(navController: NavController, viewModel: TimetableViewModel = viewModel()) {
    var subject by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Timetable Entry") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            TextField(value = subject, onValueChange = { subject = it }, label = { Text("Subject") })
            TextField(value = time, onValueChange = { time = it }, label = { Text("Time") })
            TextField(value = location, onValueChange = { location = it }, label = { Text("Location") })

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (subject.isNotEmpty() && time.isNotEmpty() && location.isNotEmpty()) {
                    viewModel.addTimetableEntry(Timetable(subject = subject, time = time, location = location))
                    navController.popBackStack()
                }
            }) {
                Text("Save")
            }
        }
    }
}
*/