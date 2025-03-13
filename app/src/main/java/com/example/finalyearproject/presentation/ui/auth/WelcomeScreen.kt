package com.example.finalyearproject.presentation.ui.auth

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalyearproject.presentation.viewmodel.AuthViewModel

@Composable
fun WelcomeScreen(authViewModel: AuthViewModel = viewModel(), onContinue: () -> Unit) {
    val context = LocalContext.current
    val username = authViewModel.getUsername(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, $username!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { onContinue() }) {
            Text("Go to Dashboard")
        }
    }
}
