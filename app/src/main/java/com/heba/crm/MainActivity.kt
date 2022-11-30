package com.heba.crm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var actionType: EditText
    lateinit var detailsTV: TextView
    lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstName = findViewById(R.id.firstNameET)
        lastName = findViewById(R.id.lastNameET)
        actionType = findViewById(R.id.actionET)
        detailsTV = findViewById(R.id.detailsTV)
        save = findViewById(R.id.button)
        readInfo()
        save.setOnClickListener {
            saveInfo()
        }
    }

    private fun saveInfo() {
        if (firstName.text.isEmpty()) {
            firstName.error = "Please enter customer first Name"
        }
        if (lastName.text.isEmpty()) {
            lastName.error = "Please enter customer last Name"
        }
        if (actionType.text.isEmpty()) {
            actionType.error = "Please enter customer action Type"
        }
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val editSp = sp.edit()
        editSp.putString("firstName", firstName.text.toString())
        editSp.putString("lastName", lastName.text.toString())
        editSp.putString("actionType", actionType.text.toString())
        editSp.apply()
        Toast.makeText(this, "Saved !!", Toast.LENGTH_LONG).show()

    }

    private fun readInfo() {
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val first = sp.getString("firstName", "")
        val last = sp.getString("lastName", "")
        val type = sp.getString("actionType", "")
        firstName.setText(first)
        lastName.setText(last)
        actionType.setText(type)
        detailsTV.text= "$first $last \n $type"


    }
}