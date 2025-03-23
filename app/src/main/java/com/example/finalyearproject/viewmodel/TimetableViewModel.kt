package com.example.finalyearproject.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.data.excel.ExcelParser
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.TimetableRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class TimetableViewModel(private val repository: TimetableRepository) : ViewModel() {
    // ✅ Live state of extracted timetable
    private val _timetableData = MutableStateFlow<List<TimetableEntry>>(emptyList())
    val timetableData: StateFlow<List<TimetableEntry>> = _timetableData

    // ✅ Factory to provide Repository
    class Factory(private val repository: TimetableRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TimetableViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TimetableViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun importTimetable(context: Context, uri: Uri, course: String, level: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                if (inputStream != null) {
                    Log.d("TimetableViewModel", "📂 Reading file: $uri")

                    val fileSize = inputStream.available()
                    Log.d("TimetableViewModel", "📏 File size: $fileSize bytes")

                    if (fileSize == 0) {
                        Log.e("TimetableViewModel", "❌ Error: File is empty!")
                        return@launch
                    }

                    val timetableData = ExcelParser.parseExcelFile(inputStream, course, level)

                    if (timetableData.isEmpty()) {
                        Log.e("TimetableViewModel", "❌ Parsed data is empty!")
                    } else {
                        Log.d("TimetableViewModel", "✅ Successfully parsed timetable: $timetableData")

                        // ✅ Update the StateFlow with new timetable data
                        _timetableData.value = timetableData
                    }
                } else {
                    Log.e("TimetableViewModel", "❌ Error: Unable to open file stream!")
                }
            } catch (e: Exception) {
                Log.e("TimetableViewModel", "❌ Exception during file import", e)
            }
        }
    }
}
