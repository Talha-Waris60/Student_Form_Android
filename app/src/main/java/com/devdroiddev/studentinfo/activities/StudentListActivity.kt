package com.devdroiddev.studentinfo.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devdroiddev.studentinfo.adapters.StudentAdapter
import com.devdroiddev.studentinfo.databinding.ActivityStudentListBinding
import com.devdroiddev.studentinfo.dbclasses.StudentDB
import com.devdroiddev.studentinfo.models.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStudentListBinding
    private val studentAdapter: StudentAdapter by lazy { StudentAdapter(this) }
    private var studentList: MutableList<StudentModel> = mutableListOf() // Declare studentList as a global property
    private lateinit var noResultsText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.floatAddBtn.setOnClickListener {
            startActivity(Intent(this@StudentList,FormActivity::class.java))
            finish()
        }*/
        binding.searchView.setOnQueryTextListener(object :  androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // This method is called when the user submits the search query
                // Perform search or any other action here
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // This method is called when the text in the search view changes
                // Update search suggestions or perform filtering here
                filterList(newText)
                return true
            }
        })

        binding.studentRecycler.apply {
            layoutManager = LinearLayoutManager(this@StudentListActivity)
            adapter = studentAdapter
        }
    }

    private fun filterList(newText: String?) {
        var filterStudentList : MutableList<StudentModel> = mutableListOf()
        for (studentModel in  studentList) {
            // Perform your filtering logic here
            // For example, check if the student's name contains the newText
            if (studentModel.name.contains(newText.toString(), ignoreCase = true)) {
                filterStudentList.add(studentModel)
            }
        }
        if (newText.isNullOrBlank()) {
            // Clear the filtered list and show all items when the search query is empty
            studentAdapter.setFilteredList(studentList)
            binding.noResultsText.visibility = View.GONE
        }
        else if (filterStudentList.isEmpty()) {
            // No results found
            studentAdapter.setFilteredList(filterStudentList)
            binding.noResultsText.visibility = View.VISIBLE
        }
        else {
            studentAdapter.setFilteredList(filterStudentList)
            binding.noResultsText.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            studentList = withContext(Dispatchers.IO) {
                StudentDB.getDatabase(this@StudentListActivity).studentDAO().getStudentModelData()
            }
            studentAdapter.setData(studentList)
        }
    }
}