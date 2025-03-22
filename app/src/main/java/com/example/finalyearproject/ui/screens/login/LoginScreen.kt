package com.example.finalyearproject.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.R

@Composable
fun LoginScreen(onProceed: (String) -> Unit = {}) {
    var username by remember { mutableStateOf("") } // ✅ Using remember (fixed)
    val isUsernameEntered = username.isNotBlank() // ✅ Only enables button if text is entered

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // ✅ Background Image
        Image(
            painter = painterResource(R.drawable.timetable_app_home_screen),
            contentDescription = "Login Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // ✅ Blurred Overlay (Optional)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        // ✅ Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(56.dp))

            Card(
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)), // ✅ Light Grey
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Enter Username", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))

                    // ✅ FIXED: TextField now updates username properly
                    OutlinedTextField(
                        value = username,
                        onValueChange = { input -> username = input }, // ✅ Corrected parameter
                        placeholder = { Text("@Username") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // ✅ Button only enables when a username is entered
                    Button(
                        onClick = { onProceed(username) },
                        enabled = isUsernameEntered,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isUsernameEntered) Color(0xFF757575) else Color(0xFFBDBDBD),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Proceed")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen {}
}
