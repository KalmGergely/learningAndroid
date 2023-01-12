package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i("LIFECYCLE", "SecondActivity : OnCreate")

        val userName = intent.getStringExtra("USER")
        val message = "Hello, $userName, you have no available offers yet:("

        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        tvMessage.text = message
    }

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "SecondActivity : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "SecondActivity : OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "SecondActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "SecondActivity : OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "SecondActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "SecondActivity : OnRestart")
    }
}