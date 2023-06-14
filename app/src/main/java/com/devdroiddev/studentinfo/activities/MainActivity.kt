package com.devdroiddev.studentinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash screen
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivityInstance = this

        // Show News Button
    /*    binding.newsBtn.setOnClickListener {
            val iNews = Intent(this@MainActivity, NewsActivity::class.java)
            startActivity(iNews)
        }*/

      /*  binding.addStudent.setOnClickListener {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            intent.putExtra("from", "MainActivity")
            startActivity(intent)
        }*/

        // Show Activity Button
      /*  binding.viewStudent.setOnClickListener {
            val iShow = Intent(this@MainActivity, StudentListActivity::class.java)
            startActivity(iShow)
        }*/
    }

    // TODO: Listener using Data Binding
    // Show-News using Retrofit From News Api
         fun showNews() {
         val iNews = Intent(this, NewsActivity::class.java)
         startActivity(iNews)
     }

    // open add Student Activity
    fun openAddStudentAct() {
        val intent = Intent(this@MainActivity, FormActivity::class.java)
        intent.putExtra("from", "MainActivity")
        startActivity(intent)
    }

    // Open Student List Activity
    fun openStudentListAct() {
        val iShow = Intent(this@MainActivity, StudentListActivity::class.java)
        startActivity(iShow)
    }
}