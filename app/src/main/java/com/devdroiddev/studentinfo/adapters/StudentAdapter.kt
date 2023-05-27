package com.devdroiddev.studentinfo.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.activities.FormActivity
import com.devdroiddev.studentinfo.dbclasses.StudentDB
import com.devdroiddev.studentinfo.models.StudentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentAdapter(private val context: Context) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    private var studentList = mutableListOf<StudentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.sdName.text = currentStudent.name
        holder.sdPhone.text = currentStudent.phone

        // click listener on each Item view
        holder.itemView.setOnClickListener {
            val intent = Intent(context,FormActivity::class.java)
            intent.putExtra("from", "StudentAdapter")
            intent.putExtra("student_model", currentStudent)
            context.startActivity(intent)
        }
       /* holder.deleteData.setOnClickListener {
                val db = StudentInfoDB.getDatabase(context).studentInfoDAO()
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteInfo(currentStudent)
            }
            studentList.removeAt(holder.position)
            notifyItemRemoved(holder.position )
        }*/

        holder.selectMenuIcon.setOnClickListener {
            showPopupMenu(holder.selectMenuIcon, currentStudent, position)
        }
    }

    private fun showPopupMenu(view: View, student: StudentModel, position : Int) {
        val popup = PopupMenu(context, view)
        popup.inflate(R.menu.option_menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete_opt -> {

                    // Create a dialogue box here
                    val confirmationDialog = AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setIcon(R.drawable.delete)
                        .setMessage("Are you sure you want to delete this student?")
                        .setPositiveButton("Yes") { _, _  ->
                            // Delete the student from the database
                            val db = StudentDB.getDatabase(context).studentDAO()
                            CoroutineScope(Dispatchers.IO).launch {
                                db.deleteModel(student)
                            }
                            studentList.removeAt(position)
                            notifyItemRemoved(position )
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

    // set the Filter List Here
    fun setFilteredList(filteredList : MutableList<StudentModel> ) {
            studentList = filteredList
            notifyDataSetChanged()
    }
    // Set Data Method
    fun setData(data: List<StudentModel>){
        studentList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }



    // MyViewHolder Class
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sdName : TextView = itemView.findViewById<TextView>(R.id.student_name)
        val sdPhone : TextView = itemView.findViewById<TextView>(R.id.student_phone_number)
        /*val deleteData : ImageView = itemView.findViewById(R.id.delete_item)*/
        val selectMenuIcon : ImageView = itemView.findViewById(R.id.menuOptions)
    }
}