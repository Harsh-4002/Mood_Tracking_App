package com.example.moodset

import android.annotation.SuppressLint
import android.app.DatePickerDialog


import android.app.TimePickerDialog
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodset.databinding.ActivityMainBinding

import kotlinx.coroutines.launch


import java.text.SimpleDateFormat


import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var selectedText: String = ""
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    private lateinit var button: Button
  private  var binding: ActivityMainBinding? = null

    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


 //Jumping to the history activity through history button
        binding?.historyButton?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("history_clicked", true)
            startActivity(intent)
            finish()
        }

//setting edittext to the current time
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val time = String.format("%02d:%02d", hour, minute)
        binding?.etTime?.setText(time)

        // Show a TimePickerDialog when the EditText is clicked
               binding?.etTime?.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    binding?.etTime?.setText(selectedTime)
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        // Initialize EditText and set its text to current date
        binding?.etDate?.setText(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))
        // Set click listener on EditText to show DatePickerDialog
       binding?.etDate?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
           //so that user cant select  upcoming date  this will get current-date
            val maxDate = Calendar.getInstance()


            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                binding?.etDate?.setText(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(year - 1900, month, dayOfMonth)))
            }, currentYear, currentMonth, currentDayOfMonth)

           //here i create a date-picker object that restrict user from selecting upcoming date
            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

            datePickerDialog.show()
        }
        //creating dao instance  so we can call  to add record function to save record
        val moodDao=(application as Appmod).db.mooddao()

        //Saving time  in database
binding?.saveButton?.setOnClickListener{
    addRecord(moodDao)

}

binding?.moodAnalysis?.setOnClickListener{


    val intent=Intent(this,HistoryActivity::class.java)
    startActivity(intent)
    finish()

}
    }

private fun addRecord(moodDao:MOODDao){


    val radioGroup = binding?.moodRadiogroup

// Get the ID of the selected radio button
    val selectedId = radioGroup?.checkedRadioButtonId
    var selectedText: String? = null

    if (selectedId != -1) {
        // Use the ID to get a reference to the selected radio button
        val radioButton = binding?.root?.findViewById<RadioButton>(selectedId!!)
        selectedText = radioButton?.text.toString()

    }
        // Do something with the selectedText
    // }
 else {
        // No radio button is selected
    }

// Use the selectedText variable outside of the radio group


    val time=binding?.etTime?.text.toString()
    val date=binding?.etDate?.text.toString()
    val reason=binding?.reasonEdittext?.text.toString()

  if (time.isNotEmpty() && date.isNotEmpty() &&  selectedText!!.isNotEmpty()&& reason.isNotEmpty()) {
        lifecycleScope.launch {

            moodDao.insert(moodentity(time = time, date = date, mood =selectedText, reason = reason))
            Toast.makeText(applicationContext, "Record Added", Toast.LENGTH_LONG).show()
            //     binding?.etTime?.text?.clear()
            //   binding?.etDate?.text?.clear()
        }


    } else {
        Toast.makeText(applicationContext, "Record Not Added", Toast.LENGTH_LONG).show()

    }


}






}







