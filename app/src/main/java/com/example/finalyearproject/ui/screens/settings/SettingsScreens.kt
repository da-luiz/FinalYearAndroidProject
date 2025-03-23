package com.example.finalyearproject.ui.screens.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            SettingsScreen(navController) // Pass it to your composable
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    var isDarkMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            item {
                SettingItem(
                    title = "Dark Mode",
                    trailingContent = {
                        Switch(checked = isDarkMode, onCheckedChange = { isDarkMode = it })
                    }
                )
            }

            item {
                SettingItem(
                    title = "Support",
                    onClick = { /* Open support page */ }
                )
            }

            item {
                SettingItem(
                    title = "Send Feedback",
                    onClick = { /* Open feedback form */ }
                )
            }

            item {
                SettingItem(
                    title = "About App",
                    onClick = { /* Show app information */ }
                )
            }
        }
    }
}

@Composable
fun SettingItem(title: String, onClick: (() -> Unit)? = null, trailingContent: @Composable (() -> Unit)? = null) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            trailingContent?.invoke()
        }
    }
}
