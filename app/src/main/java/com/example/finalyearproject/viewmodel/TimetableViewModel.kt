package com.example.finalyearproject.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.domain.repository.TimetableRepository
import kotlinx.coroutines.launch

class TimetableViewModel(private val repository: TimetableRepository) : ViewModel() {

    fun importTimetable(context: Context, uri: Uri, course: String, level: String) {
        viewModelScope.launch {
            repository.importTimetableFromExcel(context, uri, course, level) // ✅ Correct order
        }
    }

}
