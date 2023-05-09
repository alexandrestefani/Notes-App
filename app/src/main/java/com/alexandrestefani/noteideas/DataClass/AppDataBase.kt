package com.alexandrestefani.noteideas.DataClass

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexandrestefani.noteideas.Model.Notes
import com.alexandrestefani.noteideas.Model.Users

@Database(
    entities = [
        Notes::class,
        Users::class
    ],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    abstract fun usersDao(): UsersDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun instance(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            )
                .allowMainThreadQueries()
                .build().also {
                    db = it
                }
        }
    }
}