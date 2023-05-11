package com.devdroiddev.studentinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityFormBinding
import com.devdroiddev.studentinfo.databinding.ActivityMainBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Gender Drop Down List
        val gender = listOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, R.layout.drop_down_list, gender)
        binding.genderDropDown.setAdapter(adapter)

        // TODO: Degree Drop Down List
        val degree = listOf("Software Engineering", "Computer Science", "Information Technology")
        val adapterDegree = ArrayAdapter(this, R.layout.drop_down_list, degree)
        binding.degreeDropDown.setAdapter(adapterDegree)

        // TODO: Degree Drop Down List
        val grade = listOf('A', 'B', 'C')
        val adapterGrade = ArrayAdapter(this, R.layout.drop_down_list, grade)
        binding.gradeDropDown.setAdapter(adapterGrade)
    }
}