package com.example.finalyearproject.ui.screens.fileimport

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // ✅ Import this for context
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalyearproject.viewmodel.TimetableViewModel

@Composable
fun FileImportScreen(viewModel: TimetableViewModel = viewModel()) {
    val context = LocalContext.current // ✅ Get the Context
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var course by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var importSuccess by remember { mutableStateOf(false) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Course Name")
        TextField(value = course, onValueChange = { course = it }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Enter Level")
        TextField(value = level, onValueChange = { level = it }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { filePickerLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") }) {
            Text("Import .xlsx File")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedFileUri?.let { uri ->
            Text(text = "Selected file: $uri")

            Button(onClick = {
                if (course.isNotEmpty() && level.isNotEmpty()) {
                    viewModel.importTimetable(context, uri, course, level) // ✅ Correct order
                    importSuccess = true
                }
            }) {
                Text("Process File")
            }
        }

        if (importSuccess) {
            Text(text = "Import Successful!", color = Color.Green)
        }
    }
}
