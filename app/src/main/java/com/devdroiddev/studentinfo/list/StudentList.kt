package com.devdroiddev.studentinfo.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.activities.FormActivity
import com.devdroiddev.studentinfo.databinding.ActivityStudentListBinding
import com.devdroiddev.studentinfo.dbclasses.StudentInfoDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentList : AppCompatActivity() {

    private lateinit var binding : ActivityStudentListBinding
    private val studentAdapter: StudentAdapter by lazy { StudentAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatAddBtn.setOnClickListener {
            startActivity(Intent(this@StudentList,FormActivity::class.java))
            finish()
        }

    binding.studentRecycler.apply {
        layoutManager = LinearLayoutManager(this@StudentList)
        adapter = studentAdapter
    }
}

override fun onResume() {
    super.onResume()
    lifecycleScope.launch {
        val studentList = withContext(Dispatchers.IO) {
            StudentInfoDB.getDatabase(this@StudentList).studentInfoDAO().getStudentInfoData()
        }
        studentAdapter.setData(studentList)
    }
  }
}
