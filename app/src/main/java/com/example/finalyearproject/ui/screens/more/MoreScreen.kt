package com.example.finalyearproject.ui.screens.more

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.finalyearproject.ui.screens.navigation.Screen

@Composable
fun MoreScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),  // ✅ Now Modifier is correctly referenced
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Screen.Settings.route) }) {
            Text("Settings")
        }
        Button(onClick = { navController.navigate(Screen.Schedule.route) }) {
            Text("Schedule")
        }
        Button(onClick = { navController.navigate(Screen.Profile.route) }) {
            Text("Profile")
        }
    }
}
