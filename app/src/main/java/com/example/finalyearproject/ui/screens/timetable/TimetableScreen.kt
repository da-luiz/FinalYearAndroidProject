import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TimetableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimetableScreen()
        }
    }
}

// Sample Data Model
data class TimetableEntry(
    val courseName: String,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val venue: String
)

// Sample Timetable Data
val sampleTimetable = listOf(
    TimetableEntry("Mathematics", "8:00 AM", "9:30 AM", "Monday", "Room 101"),
    TimetableEntry("Physics", "10:00 AM", "11:30 AM", "Monday", "Lab 3"),
    TimetableEntry("Computer Science", "12:00 PM", "1:30 PM", "Tuesday", "Room 202"),
    TimetableEntry("English", "2:00 PM", "3:30 PM", "Wednesday", "Room 105"),
    TimetableEntry("Biology", "4:00 PM", "5:30 PM", "Thursday", "Lab 2"),
)

@Composable
fun TimetableScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Text(
            text = "Weekly Timetable",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(sampleTimetable) { entry ->
                TimetableCard(entry)
            }
        }
    }
}

@Composable
fun TimetableCard(entry: TimetableEntry) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Material 3
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.courseName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${entry.startTime} - ${entry.endTime}")
            Text(text = "Day: ${entry.dayOfWeek}")
            Text(text = "Venue: ${entry.venue}")
        }
    }
}
