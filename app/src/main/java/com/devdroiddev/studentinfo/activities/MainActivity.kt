package com.devdroiddev.studentinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devdroiddev.studentinfo.databinding.ActivityMainBinding
import com.devdroiddev.studentinfo.list.StudentList


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        // Splash screen
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addStudent.setOnClickListener {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            startActivity(intent)
        }

        // Show Activity Button
        binding.viewStudent.setOnClickListener {
            val iShow = Intent(this@MainActivity, StudentList::class.java)
            startActivity(iShow)
        }
    }
}