package com.example.finalyearproject.ui.screens.fileimport

import com.google.gson.Gson
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalyearproject.R
import com.example.finalyearproject.data.excel.ExcelParser
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.ui.screens.navigation.Screen
import com.example.finalyearproject.viewmodel.TimetableViewModel

@Composable
fun FileImportScreen(navController: NavController,viewModel: TimetableViewModel) {
    val context = LocalContext.current
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var course by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var importSuccess by remember { mutableStateOf(false) }
    var timetableData by remember { mutableStateOf<List<TimetableEntry>?>(null) }
    //val extractedTimetable: List<TimetableEntry> = ExcelParser.parseExcelFile(inputStream, course, level)


    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pexels_markoklaric_6408282),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xAAFFFFFF)),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Enter Course Name", color = Color.Black)
                    TextField(value = course, onValueChange = { course = it }, modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Enter Level", color = Color.Black)
                    TextField(value = level, onValueChange = { level = it }, modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { filePickerLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") }) {
                        Text("Import .xlsx File")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    selectedFileUri?.let { uri ->
                        Text("Selected file: $uri", color = Color.Black)

                        Button(onClick = {
                            if (course.isNotEmpty() && level.isNotEmpty() && selectedFileUri != null) {
                                Log.d("FileImportScreen", "Processing file: $selectedFileUri")

                                val inputStream = context.contentResolver.openInputStream(selectedFileUri!!)
                                if (inputStream != null) {
                                    val extractedTimetable: List<TimetableEntry> = ExcelParser.parseExcelFile(inputStream, course, level)

                                    if (extractedTimetable.isNotEmpty()) {
                                        Log.d("FileImportScreen", "✅ Import Successful! Data: $extractedTimetable")

                                        // ✅ Store the extracted timetable
                                        timetableData = extractedTimetable

                                        // ✅ Convert to JSON and Navigate
                                        val jsonTimetable = Uri.encode(Gson().toJson(extractedTimetable))
                                        // Wherever you navigate to the timetable screen (probably in FileImportScreen):
                                        navController.navigate("${Screen.Schedule.route}/${jsonTimetable}")
                                        //navController.navigate("timetableScreen/$jsonTimetable")
                                    } else {
                                        Log.e("FileImportScreen", "❌ No timetable data found!")
                                    }
                                } else {
                                    Log.e("FileImportScreen", "Error: Unable to open file stream!")
                                }
                            } else {
                                Log.e("FileImportScreen", "Missing input fields")
                            }
                        }) {
                            Text("Process File")
                        }



                        Spacer(modifier = Modifier.height(16.dp))

                        timetableData?.let { timetable ->
                            Column {
                                Text(text = "Extracted Timetable", color = Color.Black, style = MaterialTheme.typography.titleLarge)

                                timetable.forEach { entry ->
                                    Text(
                                        text = "${entry.day} - ${entry.startTime} to ${entry.endTime}",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Text(text = "Course: ${entry.courseName}", color = Color.DarkGray)
                                    Text(text = "Venue: ${entry.venue}", color = Color.DarkGray)
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                    }

                    if (importSuccess) {
                        Text("✅ Import Successful!", color = Color.Green)
                    }
                }
            }
        }
    }
}
