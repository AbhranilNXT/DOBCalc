package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
import android.widget.Toast
import com.example.dobcalc.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null


    //private var tvSelectedDate : TextView? = null
    //private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
        _binding!!.tvAgeInMinutes
        _binding!!.tvSelectedDate
        _binding!!.datePickerButton.setOnClickListener {



        //val datePickerButton: Button= findViewById(R.id.datePickerButton)
        //tvSelectedDate = findViewById(R.id.tvSelectedDate)
        //tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        //datePickerButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

             val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(this,
                    "Year was $selectedYear Month was ${selectedMonth+1} Day was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                _binding?.tvSelectedDate?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val datePicked = dateFormat.parse(selectedDate)

                datePicked?.let {

                    val timeInMinutes = datePicked.time /60000
                    val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {

                        val currentDateInMinutes = currentDate.time /60000

                        val minutesDifference = currentDateInMinutes-timeInMinutes

                        _binding?.tvAgeInMinutes?.text = minutesDifference.toString()
                    }
                }


            },
            year,
            month,
            day

        ).show()
    }
}