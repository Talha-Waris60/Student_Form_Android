package com.devdroiddev.studentinfo.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.activities.FormActivity
import com.devdroiddev.studentinfo.activities.ShowStudentDataActivity
import com.devdroiddev.studentinfo.dbclasses.StudentInfo
import com.devdroiddev.studentinfo.dbclasses.StudentInfoDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentAdapter(private val context: Context) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    private var studentList = mutableListOf<StudentInfo>()

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
        holder.deleteData.setOnClickListener {
                val db = StudentInfoDB.getDatabase(context).studentInfoDAO()
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteInfo(currentStudent)
            }
            studentList.removeAt(holder.position)
            notifyItemRemoved(holder.position )
        }
    }


    // Set Data Method
    fun setData(data: List<StudentInfo>){
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
        val deleteData : ImageView = itemView.findViewById(R.id.delete_item)
    }
}