package com.devdroiddev.studentinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityFormBinding
import com.devdroiddev.studentinfo.dbclasses.StudentDB
import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.models.StudentModel
import com.devdroiddev.studentinfo.utils.Helper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private lateinit var studentModel: StudentModel
    private val emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val APP_TAG = "Student_INFO"
    var from = ""
    // private val phoneNumberPattern: String = "\\+92-3XX-1234567"
    // private var isAllFieldsChecked : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)

      /*  studentModel = intent.getParcelableExtra<StudentModel>("student_model")!!
        binding.studentModel = studentModel*/

        binding.formActivityInstance = this
        from = intent.getStringExtra("from") ?: ""
        // Change the Button Text on the basis of Activity
        if (from == "MainActivity"){
            binding.submitBtn.text= "Submit"
        } else {
           binding.submitBtn.text = "Update"
            studentModel = intent.getParcelableExtra<StudentModel>("student_model")!!
            binding.studentModel = studentModel
            binding.genderDropDown.setText(studentModel.gender, false)
            binding.degreeDropDown.setText(studentModel.degree, false)
            binding.gradeDropDown.setText(studentModel.grade, false)

        }

       /* // TODO: Another way to Make DropDown Menu
        val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)*/

        // TODO: Gender Drop Down List
        val gender = listOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, R.layout.drop_down_list , gender)
        binding.genderDropDown.setAdapter(adapter)


        // TODO: Degree Drop Down List
        val degree = resources.getStringArray(R.array.degree)
        val adapterDegree = ArrayAdapter(this,  R.layout.drop_down_list  , degree)
        binding.degreeDropDown.setAdapter(adapterDegree)

        // TODO: Degree Drop Down List
        val grade = listOf("A", "B", "C")
        val adapterGrade = ArrayAdapter(this,  R.layout.drop_down_list, grade)
        binding.gradeDropDown.setAdapter(adapterGrade)

        Helper.transformIntoDatePicker(this,"MM/dd/yyyy", Date(),binding.setBirthDate)

        /*binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy")
        binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy", Date())*/

        // TODO: Submit Button
       /* binding.submitBtn.setOnClickListener {
            if (from == "MainActivity") {
                // add the data into the data base
                 performAuthentication()
            } else {
                //update the data on the reuse Activity
                val existingStudent = intent.getParcelableExtra<StudentModel>("student_model")

                val updatedStudent = existingStudent?.copy(
                    name = binding.userName.text.toString(),
                    email = binding.email.text.toString(),
                    birth = binding.setBirthDate.text.toString(),
                    gender = binding.genderDropDown.text.toString(),
                    phone = binding.phoneNumber.text.toString(),
                    degree = binding.degreeDropDown.text.toString(),
                    grade = binding.gradeDropDown.text.toString(),
                    address = binding.address.text.toString(),
                    city = binding.city.text.toString(),
                    zipCode = binding.zipCode.text.toString()
                )
                val db = StudentDB.getDatabase(applicationContext).studentDAO()
                CoroutineScope(Dispatchers.IO).launch {
                    if (updatedStudent != null) {
                        db.updateModel(updatedStudent)
                        withContext(Dispatchers.Main) {
                            finish()
                        }
                    }
                }
            }
        }*/

        // Get the intent here from Student adapter
/*
        val getStudentRecord = intent.getParcelableExtra<StudentModel>("student_model")
*/

        // Set these values on EditText
       /* binding.userName.setText(getStudentRecord?.name)
        binding.email.setText(getStudentRecord?.email)
        binding.setBirthDate.setText(getStudentRecord?.birth)*/
       /* val defaultGender = getStudentRecord?.gender
        binding.genderDropDown.setText(defaultGender, false)*/
       /* binding.genderDropDown.setText(getStudentRecord?.gender)*/
//        val genderPosition = gender.indexOf(getStudentRecord?.gender)
//        binding.genderDropDown.setSelection(genderPosition)
/*
        binding.phoneNumber.setText( getStudentRecord?.phone)
*/
        /*val defaultDegree = getStudentRecord?.degree
        binding.degreeDropDown.setText(defaultDegree, false)
       *//* val degreePosition = degree.indexOf(getStudentRecord?.degree)
        binding.degreeDropDown.setSelection(degreePosition)*//*
        val defaultGrade = getStudentRecord?.grade
        binding.gradeDropDown.setText(defaultGrade,false)*/
        /*val gradePosition = grade.indexOf(getStudentRecord?.grade)
        binding.gradeDropDown.setSelection(gradePosition)*/
   /*     binding.address.setText(getStudentRecord?.address)
        binding.city.setText(getStudentRecord?.city)
        binding.zipCode.setText(getStudentRecord?.zipCode)*/
    }

    fun addAndUpdateStudentBtn() {

        Log.d(APP_TAG, "addAndUpdateStudentBtn")
        if (from == "MainActivity") {
            // add the data into the data base
            performAuthentication()
        } else {
            //update the data on the reuse Activity
//            studentModel = intent.getParcelableExtra<StudentModel>("student_model")!!
            val updatedStudent = studentModel?.copy(
                name = binding.userName.text.toString(),
                email = binding.email.text.toString(),
                birth = binding.setBirthDate.text.toString(),
                gender = binding.genderDropDown.text.toString(),
                phone = binding.phoneNumber.text.toString(),
                degree = binding.degreeDropDown.text.toString(),
                grade = binding.gradeDropDown.text.toString(),
                address = binding.address.text.toString(),
                city = binding.city.text.toString(),
                zipCode = binding.zipCode.text.toString()
            )
            val db = StudentDB.getDatabase(applicationContext).studentDAO()
            CoroutineScope(Dispatchers.IO).launch {
                if (updatedStudent != null) {
                    db.updateModel(updatedStudent)
                    withContext(Dispatchers.Main) {
                        finish()
                    }
                }
            }
        }
    }
    // TODO: Method to Perform Authentication on Form
    private fun performAuthentication() {

        // TODO: Get Data from Edittext and store them into variables
        val userName: String = binding.userName.text.toString()
        val email: String = binding.email.text.toString().trim()
        val birth: String = binding.setBirthDate.text.toString()
        val gender: String = binding.genderDropDown.text.toString()
        val phoneNumber: String = binding.phoneNumber.text.toString()
        val degree: String = binding.degreeDropDown.text.toString()
        val grade: String = binding.gradeDropDown.text.toString()
        val address: String = binding.address.text.toString()
        val city: String = binding.city.text.toString()
        val zipCode: String = binding.zipCode.text.toString()

        // TODO: Check validation Using If-Nested Statements
        if (userName.isNotEmpty()) {
            Log.d(APP_TAG, "UserName is not Empty")
            if (email.isNotEmpty()) {
                Log.d(APP_TAG, "Email is not Empty")
                if (email.matches(emailPattern.toRegex())) {
                    Log.d(APP_TAG, "Email Pattern is matches")
                    if (phoneNumber.isNotEmpty()) {
                        Log.d(APP_TAG, "Phone Number is not Empty")
                        if (address.isNotEmpty()) {
                            Log.d(APP_TAG, "Address is not Empty")
                            if (city.isNotEmpty()) {
                                Log.d(APP_TAG, "City is not Empty")
                                if (zipCode.isNotEmpty()) {
                                    Log.d(APP_TAG, "Zip code is not Empty")
                                    if (Helper.isInternetAvailable(this)) {
                                        Log.d(APP_TAG, "Internet is available")

                                        // Create an instance of the database and push the data to database
                                        // TODO: Calling the Object of dataclass
                                        val studentData = StudentModel(name = userName, email = email, birth = birth, gender = gender, phone = phoneNumber, degree = degree,
                                            grade = grade, address = address, city = city, zipCode = zipCode)

                                        // TODO: Database Instance
                                            val studentDao = StudentDB.getDatabase(applicationContext).studentDAO()
                                            CoroutineScope(Dispatchers.IO).launch {
                                                studentDao.insertModel(studentData)
                                                Log.d(APP_TAG, "Student inserted into the database")
                                                withContext(Dispatchers.Main){
                                                    // Method to move on the other Activity
                                                       moveToStudentListActivity()
                                                }
                                        }
                                    } else {
                                        Helper.showSnackBar(
                                            binding.root,
                                            "Internet is not Available"
                                        )
                                    }
                                } else {
                                    binding.zipCode.error = "Field is Empty"
                                }
                            } else {
                                binding.city.error = "Field is Empty"
                            }
                        } else {
                            binding.address.error = "Field is Empty"
                        }
                    } else {
                        binding.phoneNumber.error = "Field is Empty"
                    }
                } else {
                    binding.email.error = "Enter Correct Email"
                }
            } else {
                binding.email.error = "Field is Empty"
            }
        } else {
            binding.userName.error = "Field is Empty"
        }
    }

    private fun moveToStudentListActivity() {
        startActivity(Intent(this@FormActivity, StudentListActivity::class.java))
        finish()
    }

    // Method of ClickListener
    // Add Student and Update Student Button

    /*
    if (userName.isEmpty()) {
        binding.userName.error = "Field is Empty"
    }
    if (email.isEmpty()) {
        binding.email.error = "Field is Empty"
    } else if (!email.matches(emailPattern.toRegex())) {
        binding.email.error = "Enter Correct Email"
    }
    if (phoneNumber.isEmpty()) {
        binding.phoneNumber.error = "Field is Empty"
    } else if (phoneNumber.isEmpty()){
        binding.phoneNumber.error = "Enter Correct Number"
    }
    if (address.isEmpty()) {
        binding.address.error = "Field is Empty"
    }
    if (city.isEmpty()) {
        binding.city.error = "Field is Empty"
    }
    if (zipCode.isEmpty()) {
        binding.zipCode.error = "Field is Empty"
        if (isInternetAvailable()) {
           showSnackBar("Internet is Available")
        }
        else showSnackBar("Internet is not Available")
    }
}*/


    }

