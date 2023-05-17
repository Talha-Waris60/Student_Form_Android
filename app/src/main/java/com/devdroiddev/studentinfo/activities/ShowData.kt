package com.devdroiddev.studentinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityShowDataBinding
import com.devdroiddev.studentinfo.dbclasses.StudentInfo
import com.devdroiddev.studentinfo.dbclasses.StudentInfoDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowData : AppCompatActivity() {
    lateinit var binding: ActivityShowDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}