package com.example.oopdemo

import android.util.Log

class MyCar: Car(), SpeedController {
    override fun start() {
        Log.i("tag", "My car is starting... brand id is ${getBrandId()}")
    }

    override fun accelerate() {
        TODO("Not yet implemented")
    }

    override fun decelerate() {
        TODO("Not yet implemented")
    }
}