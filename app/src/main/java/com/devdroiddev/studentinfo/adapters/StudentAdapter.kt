package com.devdroiddev.studentinfo.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.activities.FormActivity
import com.devdroiddev.studentinfo.databinding.StudentItemRowBinding
import com.devdroiddev.studentinfo.dbclasses.StudentDB
import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.interfaces.PopMenu
import com.devdroiddev.studentinfo.models.StudentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentAdapter(private val context: Context, private val studentList: MutableList<StudentModel>,
                     private val itemClickListener: OnItemClickListener<StudentModel>, private val menuItem : PopMenu<StudentModel>
) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val inflater= LayoutInflater.from(parent.context)
       val view = inflater.inflate(R.layout.student_item_row, parent, false)
       return MyViewHolder(view)*/

        // Applying View Binding
        val inflater = LayoutInflater.from(parent.context)
        val binding: StudentItemRowBinding = StudentItemRowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.binding.studentItemRow = currentStudent
        holder.binding.clickListener = itemClickListener
        holder.binding.menu = menuItem
        holder.binding.position = position
        holder.binding.executePendingBindings()

        /*holder.itemView.setOnClickListener{
            itemClickListener.onItemClicked(currentStudent)
        }*/


        /* holder.binding.studentName.text = currentStudent.name
        holder.binding.studentPhoneNumber.text = currentStudent.phone*/

        // click listener on each Item view
       /* holder.itemView.setOnClickListener {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra("student_model", currentStudent)
            context.startActivity(intent)
        }*/
        /* holder.deleteData.setOnClickListener {
                val db = StudentInfoDB.getDatabase(context).studentInfoDAO()
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteInfo(currentStudent)
            }
            studentList.removeAt(holder.position)
            notifyItemRemoved(holder.position )
        }*/

       /* holder.binding.menuOptions.setOnClickListener {
            *//*showPopupMenu(holder.binding.menuOptions, currentStudent, position)*//*
            menuItem.showMenu(holder.binding.menuOptions, currentStudent, position)
        }*/
    }

/*    private fun showPopupMenu(view: View, student: StudentModel, position: Int) {
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
                        .setPositiveButton("Yes") { _, _ ->
                            // Delete the student from the database
                            val db = StudentDB.getDatabase(context).studentDAO()
                            CoroutineScope(Dispatchers.IO).launch {
                                db.deleteModel(student)
                            }
                            studentList.removeAt(position)
                            notifyItemRemoved(position)
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
    }*/

    // MyViewHolder Class
    class MyViewHolder(val binding: StudentItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        /* val sdName : TextView =  binding.studentName
        val sdPhone : TextView = binding.studentPhoneNumber
        val selectMenuIcon : ImageView = binding.menuOptions*/
        /*val deleteData: ImageView = binding.deleteItem*/
        }
    }

