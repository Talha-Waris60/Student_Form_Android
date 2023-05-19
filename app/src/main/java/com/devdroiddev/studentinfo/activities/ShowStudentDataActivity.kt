package com.devdroiddev.studentinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityShowStudentDataBinding
import com.devdroiddev.studentinfo.databinding.ActivityStudentListBinding
import com.devdroiddev.studentinfo.dbclasses.StudentInfoDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowStudentDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowStudentDataBinding
    private var studentId: Int = 0 // Assuming the student ID is passed as an extra
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStudentDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        // Get the intent here that is on array adapter
//        val name = intent.getStringExtra("name")
//        val degree = intent.getStringExtra("degree")
//        val email = intent.getStringExtra("email")
//        val gender = intent.getStringExtra("gender")
//        val grade = intent.getStringExtra("grade")
//        val city = intent.getStringExtra("city")
//        studentId = intent.getIntExtra("studentId", 0)
//
//        // Set these values on textView
//        binding.showStudentName.text = name
//        binding.showStudentDegree.text = degree
//        binding.showStudentEmail.text = email
//        binding.showStudentGender.text = gender
//        binding.showStudentGrade.text = grade
//        binding.showStudentCity.text = city

        // Update the Student Data
        binding.updateBtn.setOnClickListener {

        }

        // Delete the Student Data
        binding.deleteBtn.setOnClickListener {
            deleteStudent()
        }
    }

    private fun deleteStudent() {
        GlobalScope.launch(Dispatchers.IO) {
            val studentDAO = StudentInfoDB.getDatabase(applicationContext).studentInfoDAO().deleteStudentById(1)
        }
        // Finish the activity or perform any other actions after deletion
        finish()
    }
}