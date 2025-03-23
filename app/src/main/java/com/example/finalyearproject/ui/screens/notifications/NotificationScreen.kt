package com.example.finalyearproject.ui.screens.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NotificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Create NavController instance
            NotificationsScreen(navController) // Pass it to your composable
        }
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    val notifications = listOf(
        "Assignment due tomorrow!",
        "New timetable available",
        "Reminder: Submit project proposal",
        "Class rescheduled to 10 AM"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Notifications",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(notifications) { notification ->
                NotificationCard(notification)
            }
        }
    }
}

@Composable
fun NotificationCard(message: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = message,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}