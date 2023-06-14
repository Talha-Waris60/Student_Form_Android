package com.devdroiddev.studentinfo.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.devdroiddev.studentinfo.models.StudentModel

@Dao
interface StudentDAO {

    @Insert
    suspend fun insertModel(studentModel: StudentModel)

    @Update
    suspend fun updateModel(studentModel: StudentModel)

    @Delete
    suspend fun deleteModel(studentModel: StudentModel)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllModel()

    @Query("DELETE FROM student_table WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Int)

    @Query("SELECT * FROM student_table ORDER BY id DESC" )   // Compile verification of Queries
    fun getStudentModelData() : MutableList<StudentModel>
}
