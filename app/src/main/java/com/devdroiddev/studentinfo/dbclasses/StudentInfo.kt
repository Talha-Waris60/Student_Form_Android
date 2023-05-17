package com.devdroiddev.studentinfo.dbclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email : String,
    val birth : String,
    val gender : String,
    val phone : String,
    val degree : String,
    val grade : String,
    val address : String,
    val city : String,
    val zipCode: String

)