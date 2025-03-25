package com.example.finalyearproject.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

// Create the Screen sealed class if it's missing in your project
sealed class Screen(val route: String) {
    // Main bottom nav items
    object Home : Screen("home")
    object Assignments : Screen("assignments")
    object Notifications : Screen("notifications")
    object More : Screen("more") // This will be our container for other screens

    // Screens nested under "More"
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Schedule : Screen("schedule")
}

@Composable
fun BottomNavBar(navController: NavController) {
    // Only these 4 items should appear in the bottom bar
    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Assignments,
        Screen.Notifications,
        Screen.More
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF3D7971),
        contentColor = Color.White
    ) {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Home -> Icon(Icons.Filled.Home, contentDescription = "Home")
                        Screen.Assignments -> Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Assignments")
                        Screen.Notifications -> Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                        Screen.More -> Icon(Icons.Filled.Menu, contentDescription = "More")
                        // Explicitly exclude these from bottom bar
                        Screen.Profile -> Unit
                        Screen.Settings -> Unit
                        Screen.Schedule -> Unit
                    }
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercaseChar() }) },
                selected = currentRoute == screen.route ||
                        (screen == Screen.More && currentRoute in listOf(
                            Screen.Profile.route,
                            Screen.Settings.route,
                            Screen.Schedule.route
                        )),
                onClick = {
                    if (currentRoute != screen.route) {
                        val route = when (screen) {
                            Screen.More -> Screen.Profile.route // Default to Profile when More is clicked
                            else -> screen.route
                        }
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}