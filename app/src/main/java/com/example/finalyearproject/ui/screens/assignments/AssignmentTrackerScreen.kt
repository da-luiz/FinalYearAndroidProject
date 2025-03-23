package com.example.finalyearproject.ui.screens.assignments

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

class AssignmentTrackerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Create NavController instance
            AssignmentTrackerScreen(navController) // Pass it to your composable
        }
    }
}

// Sample Data Model
data class Assignment(
    val courseName: String,
    val title: String,
    val dueDate: String
)

// Sample Assignment Data
val sampleAssignments = listOf(
    Assignment("Mathematics", "Algebra Worksheet", "March 20, 2025"),
    Assignment("Physics", "Lab Report", "March 22, 2025"),
    Assignment("Computer Science", "Project Proposal", "March 25, 2025"),
    Assignment("English", "Essay on Literature", "March 28, 2025")
)

@Composable
fun AssignmentTrackerScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Text(
            text = "Assignment Tracker",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(sampleAssignments) { assignment ->
                AssignmentCard(assignment)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Assignment Button
        Button(
            onClick = { /* Handle adding new assignment */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Add New Assignment")
        }
    }
}

@Composable
fun AssignmentCard(assignment: Assignment) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Material 3
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = assignment.courseName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Title: ${assignment.title}")
            Text(text = "Due Date: ${assignment.dueDate}")
        }
    }
}