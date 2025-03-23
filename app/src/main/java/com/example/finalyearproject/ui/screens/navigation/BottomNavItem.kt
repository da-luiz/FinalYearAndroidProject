package com.example.finalyearproject.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, "Home")
    object Assignments : BottomNavItem("assignments", Icons.Filled.List, "Assignments")
    object Notifications : BottomNavItem("notifications", Icons.Filled.Notifications, "Notifications")
    object More : BottomNavItem("more", Icons.Filled.Menu, "More")
}
