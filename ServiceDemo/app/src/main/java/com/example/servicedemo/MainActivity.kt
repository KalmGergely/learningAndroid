package com.example.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicedemo.BackgroundService.Companion.tag
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, BackgroundService::class.java)
        var startedNum = 0
        serviceIntent.putExtra("INFO", "Service name:")

        binding.apply {
            btnStart.setOnClickListener() {
                Log.i(tag, "Starting service")
                startedNum++
                serviceIntent.putExtra("STARTNUM", startedNum)
                startService(serviceIntent)
            }

            btnStop.setOnClickListener() {
                Log.i(tag, "Stopping service")
                stopService(serviceIntent)
            }
        }

    }
}