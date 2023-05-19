package com.devdroiddev.studentinfo.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityFormBinding
import com.devdroiddev.studentinfo.databinding.ActivityMainBinding
import com.devdroiddev.studentinfo.dbclasses.StudentInfo
import com.devdroiddev.studentinfo.dbclasses.StudentInfoDB
import com.devdroiddev.studentinfo.list.StudentList
import com.devdroiddev.studentinfo.utils.Helper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private val emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val APP_TAG = "Student_INFO"
    var from = ""
    // private val phoneNumberPattern: String = "\\+92-3XX-1234567"
    // private var isAllFieldsChecked : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        from = intent.getStringExtra("from") ?: ""

        if (from == "MainActivity"){
            binding.submitBtn.text= "Submit"
        } else {
           binding.submitBtn.text = "Update"
        }
        // TODO: Another way to Make DropDown Menu
        /*val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)*/

        // TODO: Gender Drop Down List
        val gender = listOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, R.layout.drop_down_list, gender)
        binding.genderDropDown.setAdapter(adapter)

        // TODO: Degree Drop Down List
        val degree = resources.getStringArray(R.array.degree)
        val adapterDegree = ArrayAdapter(this, R.layout.drop_down_list, degree)
        binding.degreeDropDown.setAdapter(adapterDegree)

        // TODO: Degree Drop Down List
        val grade = listOf('A', 'B', 'C')
        val adapterGrade = ArrayAdapter(this, R.layout.drop_down_list, grade)
        binding.gradeDropDown.setAdapter(adapterGrade)

        Helper.transformIntoDatePicker(this,"MM/dd/yyyy", Date(),binding.setBirthDate)

        /*binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy")
        binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy", Date())*/

        // TODO: Submit Button
        binding.submitBtn.setOnClickListener {
            if (from == "MainActivity") {
                // add the data into the data base
                 performAuthentication()
            } else {
                //update the data
                val existingStudent = intent.getParcelableExtra<StudentInfo>("student_model")

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
                val db = StudentInfoDB.getDatabase(applicationContext).studentInfoDAO()
                CoroutineScope(Dispatchers.IO).launch {
                    if (updatedStudent != null) {
                        db.updateInfo(updatedStudent)
                        withContext(Dispatchers.Main) {
                            finish()
                        }
                    }
                }
            }
        }

        // Get the intent here that is on array adapter
        val getStudentRecord = intent.getParcelableExtra<StudentInfo>("student_model")

        // Set these values on EditText
        binding.userName.setText(getStudentRecord?.name)
        binding.email.setText(getStudentRecord?.email)
        binding.setBirthDate.setText(getStudentRecord?.birth)
        binding.genderDropDown.setText(getStudentRecord?.gender)
        binding.phoneNumber.setText( getStudentRecord?.phone)
        binding.degreeDropDown.setText(getStudentRecord?.degree)
        binding.gradeDropDown.setText(getStudentRecord?.grade)
        binding.address.setText(getStudentRecord?.address)
        binding.city.setText(getStudentRecord?.city)
        binding.zipCode.setText(getStudentRecord?.zipCode)
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
                                        val studentData = StudentInfo(name = userName, email = email, birth = birth, gender = gender, phone = phoneNumber, degree = degree,
                                            grade = grade, address = address, city = city, zipCode = zipCode)

                                        // TODO: Database Instance
                                            val studentDao = StudentInfoDB.getDatabase(applicationContext).studentInfoDAO()
                                            CoroutineScope(Dispatchers.IO).launch {
                                                studentDao.insertInfo(studentData)
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
        startActivity(Intent(this@FormActivity, StudentList::class.java))
        finish()
    }
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
        // TODO: Check for Internet accessibility




    /*   Alternative way of Performing of Validation
        TODO: validateField(userName, "Field is Empty", binding.userName)
            validateField(email, "Field is Empty", binding.email)
            validateField(email.matches(emailPattern.toRegex()), "Enter Correct Email", binding.email)
            validateField(phoneNumber, "Field is Empty", binding.phoneNumber)
            validateField(phoneNumber.matches(phoneNumberPattern.toRegex()), "Enter Correct Number", binding.phoneNumber)
            validateField(address, "Field is Empty", binding.address)
            validateField(city, "Field is Empty", binding.city)
            validateField(zipCode, "Field is Empty", binding.zipCode)*/

        /* private fun validateField(value: String, errorMessage: String, view: EditText) {
            if (value.isEmpty()) {
                view.error = errorMessage
            }
        }*/

        /*  Method for checking the condition for Matching pattern
         private fun validateField(condition: Boolean, errorMessage: String, view: EditText) {
            if (!condition) {
                view.error = errorMessage
            }
        }*/

    }

