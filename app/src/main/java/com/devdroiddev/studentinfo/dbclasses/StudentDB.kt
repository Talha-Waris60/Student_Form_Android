package com.devdroiddev.studentinfo.dbclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devdroiddev.studentinfo.models.StudentModel

@Database(entities = [StudentModel::class], version = 1)
abstract class StudentDB : RoomDatabase(){

    // TODO: Link the DAO to database class
    abstract fun studentDAO() : StudentDAO
    companion object {
        @Volatile
        private var INSTANCE: StudentDB? = null
        fun getDatabase(context: Context): StudentDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDB::class.java,
                    "student_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}