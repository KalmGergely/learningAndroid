package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra("USER")
        val message = "Hello, $userName, you have no available offers yet:("

        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        tvMessage.text = message
    }
}