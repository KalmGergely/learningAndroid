package com.example.viewbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.viewbindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //val tvMessage = findViewById<TextView>(R.id.tvMessage)
        //val etName = findViewById<EditText>(R.id.etName)
        //val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        binding.apply {
            btnSubmit.setOnClickListener() {
                val name = etName.text.toString()
                tvMessage.text = "Hello $name!"
                etName.text.clear()
            }
        }
    }
}