package com.example.finalyearproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalyearproject.data.local.dao.TimetableDao
import com.example.finalyearproject.data.local.entities.TimetableEntity


@Database(entities = [TimetableEntity::class], version = 2, exportSchema = false) // ⬆️ Bump the version from 1 → 2
abstract class TimetableDatabase : RoomDatabase() {
    abstract fun timetableDao(): TimetableDao

    companion object {
        @Volatile
        private var INSTANCE: TimetableDatabase? = null

        fun getInstance(context: Context): TimetableDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimetableDatabase::class.java,
                    "timetable_database"
                )
                    .fallbackToDestructiveMigration() // 🚨 WARNING: This will DELETE old data! 🚨
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

