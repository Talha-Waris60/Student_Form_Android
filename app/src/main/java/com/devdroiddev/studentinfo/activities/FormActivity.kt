package com.devdroiddev.studentinfo.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityFormBinding
import com.devdroiddev.studentinfo.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private val emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val phoneNumberPattern : String = "\\+92-3XX-1234567"
    // private var isAllFieldsChecked : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // TODO: Date Picker
        fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
            isFocusableInTouchMode = false
            isClickable = true
            isFocusable = false

            val myCalendar = Calendar.getInstance()
            val datePickerOnDataSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val sdf = SimpleDateFormat(format, Locale.UK)
                    setText(sdf.format(myCalendar.time))
                }

            setOnClickListener {
                DatePickerDialog(
                    context, datePickerOnDataSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).run {
                    maxDate?.time?.also { datePicker.maxDate = it }
                    show()
                }
            }
        }
        binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy")
        binding.setBirthDate.transformIntoDatePicker(this,"MM/dd/yyyy", Date())

        // TODO: Submit Button
        binding.submitBtn.setOnClickListener {
           performAuthentication()
        }
    }

    // TODO: Method to Perform Authentication on Form
    private fun performAuthentication() {
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
        } else if (!phoneNumber.matches(phoneNumberPattern.toRegex())){
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
        }
    }
}
