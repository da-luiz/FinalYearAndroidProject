package com.example.finalyearproject.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

// Create the Screen sealed class if it's missing in your project
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Assignments : Screen("assignments")
    object Notifications : Screen("notifications")
    object More : Screen("more")
    object Profile : Screen("profile")
    object Schedule : Screen("schedule")
    object Settings : Screen("settings")
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Assignments,
        Screen.Notifications,
        Screen.More
    )

    NavigationBar(
        containerColor = Color(0xFF3D7971),
        contentColor = Color.White
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Home -> Icon(Icons.Filled.Home, contentDescription = "Home")
                        Screen.Assignments -> Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Assignments")
                        Screen.Notifications -> Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                        Screen.More -> Icon(Icons.Filled.MoreVert, contentDescription = "More")
                        Screen.Profile -> Icon(Icons.Filled.Person, contentDescription = "Profile")
                        Screen.Schedule -> Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Schedule")
                        Screen.Settings -> Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercaseChar() }) },
                selected = false, // You can add logic to highlight the selected tab
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}