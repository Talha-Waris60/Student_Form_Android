package com.devdroiddev.studentinfo.dbclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentInfo::class], version = 1)
abstract class StudentInfoDB : RoomDatabase(){

    // TODO: Link the DAO to database class
    abstract fun studentInfoDAO() : StudentInfoDAO
    companion object {
        @Volatile
        private var INSTANCE: StudentInfoDB? = null
        fun getDatabase(context: Context): StudentInfoDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentInfoDB::class.java,
                    "student_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}