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

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Query("DELETE FROM student_table WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Int)

    @Query("SELECT * FROM student_table ORDER BY id DESC" )   // Compile verification of Queries
    fun getStudentInfoData() : List<StudentInfo>
}
