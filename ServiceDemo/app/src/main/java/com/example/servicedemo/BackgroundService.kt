package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService: Service() {
    init {
        Log.i(tag, "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val info = intent?.getStringExtra("INFO")
        val startedNum = intent?.getIntExtra("STARTNUM", 0)
        Log.i(tag, "Service started $startedNum number of times ($info BackgroundService)")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(tag, "Service destroyed")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? = null

    companion object {
        const val tag = "LogcatTag"
    }
}