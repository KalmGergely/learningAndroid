package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val greetingText = findViewById<TextView>(R.id.tvHello)
            val inputField = findViewById<EditText>(R.id.etName)
            val greetBtn = findViewById<Button>(R.id.btnGreet)
            val offersBtn = findViewById<Button>(R.id.btnOffers)

            var enteredName = ""

            greetBtn.setOnClickListener {
                enteredName = inputField.text.toString()

                if (enteredName == "") {
                    offersBtn.visibility = INVISIBLE
                    greetingText.text = ""
                    Toast.makeText(this@MainActivity,"Please enter your name!", Toast.LENGTH_SHORT).show()
                } else {
                    val message = "Welcome $enteredName!"
                    greetingText.text = message
                    inputField.text.clear()
                    offersBtn.visibility = VISIBLE
                }
            }

            offersBtn.setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USER", enteredName)
                startActivity(intent)
            }
    }
}