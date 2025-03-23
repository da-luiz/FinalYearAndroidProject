package com.example.finalyearproject.ui.screens.timetable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.viewmodel.TimetableViewModel

@Composable
fun TimetableScreen(timetableData: List<TimetableEntry>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Your Timetable", style = MaterialTheme.typography.headlineMedium)

        if (timetableData.isEmpty()) {
            Text(text = "No timetable data found.", color = Color.Red)
        } else {
            LazyColumn {
                items(timetableData) { entry ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${entry.day} - ${entry.startTime} to ${entry.endTime}", fontWeight = FontWeight.Bold)
                            Text(text = "Course: ${entry.courseName}")
                            Text(text = "Instructor: ${entry.instructor}")
                            Text(text = "Venue: ${entry.venue}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimetableCard(entry: TimetableEntry) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.courseName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${entry.startTime} - ${entry.endTime}")
            Text(text = "Day: ${entry.day}")
            Text(text = "Venue: ${entry.venue}")
        }
    }
}