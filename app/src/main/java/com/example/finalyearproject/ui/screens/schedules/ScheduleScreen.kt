package com.example.finalyearproject.ui.screens.schedules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Create NavController instance
            SchedulesScreen(navController) // Pass it to your composable
        }
    }
}

// Sample Data Model
data class Schedule(
    val eventName: String,
    val dateTime: String,
    val location: String
)

// Sample Schedule Data
val sampleSchedules = listOf(
    Schedule("Math Class", "March 20, 2025 - 8:00 AM", "Room 101"),
    Schedule("Physics Lab", "March 22, 2025 - 10:00 AM", "Lab 3"),
    Schedule("Study Session", "March 25, 2025 - 2:00 PM", "Library"),
    Schedule("Project Meeting", "March 28, 2025 - 5:00 PM", "Online")
)

@Composable
fun SchedulesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Text(
            text = "Schedules",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(sampleSchedules) { schedule ->
                ScheduleCard(schedule)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Schedule Button
        Button(
            onClick = { /* Handle adding new schedule */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Add New Schedule")
        }
    }
}

@Composable
fun ScheduleCard(schedule: Schedule) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Material 3
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = schedule.eventName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Date & Time: ${schedule.dateTime}")
            Text(text = "Location: ${schedule.location}")
        }
    }
}
