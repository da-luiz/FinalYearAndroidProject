package com.example.finalyearproject.presentation.ui.auth


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalyearproject.presentation.viewmodel.AuthViewModel

@Composable
fun UsernameScreen(authViewModel: AuthViewModel = viewModel(), onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    var username by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter your username", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (username.text.isNotEmpty()) {
                authViewModel.saveUsername(context, username.text)
                onLoginSuccess()
            } else {
                Toast.makeText(context, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Continue")
        }
    }
}