package com.devdroiddev.studentinfo.dbclasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentInfoDAO {

    @Insert
    suspend fun insertInfo(studentInfo : StudentInfo)

    @Update
    suspend fun updateInfo(studentInfo : StudentInfo)

    @Delete
    suspend fun deleteInfo(studentInfo : StudentInfo)

    @Query("SELECT * FROM student_table")   // Compile verification of Queries
    fun getStudentInfo() : LiveData<List<StudentInfo>>
}
