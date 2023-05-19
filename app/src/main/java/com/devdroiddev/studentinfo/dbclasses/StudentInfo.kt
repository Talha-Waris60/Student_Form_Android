package com.devdroiddev.studentinfo.dbclasses

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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

): Parcelable