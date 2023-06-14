package com.devdroiddev.studentinfo.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.adapters.StudentAdapter
import com.devdroiddev.studentinfo.databinding.ActivityStudentListBinding
import com.devdroiddev.studentinfo.dbclasses.StudentDB
import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.interfaces.PopMenu
import com.devdroiddev.studentinfo.models.StudentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentListActivity : AppCompatActivity(), OnItemClickListener<StudentModel>, PopMenu<StudentModel>  {

    private lateinit var binding : ActivityStudentListBinding
    private lateinit var studentAdapter: StudentAdapter
    private var studentListMain: MutableList<StudentModel> = mutableListOf()
    private var studentList: MutableList<StudentModel> = mutableListOf() // Declare studentList as a global property
    private lateinit var noResultsText: TextView
    private val APP_TAG = "Student_INFO"

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

  /*      binding.studentRecycler.apply {
            layoutManager = LinearLayoutManager(this@StudentListActivity)
            studentAdapter = StudentAdapter(this, studentList)
            adapter = studentAdapter
        }*/

        // Set the adapter on the recyclerView
        binding.studentRecycler.layoutManager = LinearLayoutManager(this@StudentListActivity)
        studentAdapter = StudentAdapter(this, studentList, this, this)
        binding.studentRecycler.adapter = studentAdapter
    }

    // Passing the data to adapter
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            studentListMain.clear()
            studentList.clear()
            studentListMain.addAll(StudentDB.getDatabase(this@StudentListActivity).studentDAO().getStudentModelData())
            studentList.addAll(studentListMain)

            withContext(Dispatchers.Main) {
                binding.studentRecycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun filterList(newText: String?) {
        studentList.clear()
        if (!newText.isNullOrEmpty()) {
            for (studentModel in studentListMain) {
                // Perform your filtering logic here
                // For example, check if the student's name contains the newText
                if (studentModel.name.contains(newText.toString(), ignoreCase = true)) {
                    studentList.add(studentModel)
                }
            }

            if (studentList.isEmpty()) {
                binding.studentRecycler.adapter?.notifyDataSetChanged()
                binding.noResultsText.visibility = View.VISIBLE
            } else {
                binding.studentRecycler.adapter?.notifyDataSetChanged()
                binding.noResultsText.visibility = View.GONE
            }
        } else {
            studentList.addAll(studentListMain)
            binding.studentRecycler.adapter?.notifyDataSetChanged()
            binding.noResultsText.visibility = View.GONE
        }

    }

    override fun onItemClicked(model: StudentModel) {
        Log.d(APP_TAG, "onItemClicked is call")
        val intent = Intent(this@StudentListActivity, FormActivity::class.java)
        intent.putExtra("student_model", model)
        startActivity(intent)
    }

    override fun showMenu(view: View, model: StudentModel, position: Int) {
        val popup = PopupMenu(this@StudentListActivity, view)
        popup.inflate(R.menu.option_menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete_opt -> {
                    // Create a dialogue box here
                    val confirmationDialog = AlertDialog.Builder(this@StudentListActivity)
                        .setTitle("Delete")
                        .setIcon(R.drawable.delete)
                        .setMessage("Are you sure you want to delete this student?")
                        .setPositiveButton("Yes") { _, _ ->
                            // Delete the student from the database
                            val db = StudentDB.getDatabase(this@StudentListActivity).studentDAO()
                            CoroutineScope(Dispatchers.IO).launch {
                                db.deleteModel(model)
                            }
                            studentList.removeAt(position)
                            studentAdapter.notifyItemRemoved(position)
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            // Cancel the deletion
                            dialog.dismiss()
                        }
                        .create()
                    confirmationDialog.setCanceledOnTouchOutside(false)
                    confirmationDialog.show()
                    true
                }

                R.id.check_opt -> {
                    // Handle check option click
                    true
                }

                R.id.linked_opt -> {
                    // Handle linked option click
                    true
                }

                else -> false
            }
        }
        popup.show()
    }


}