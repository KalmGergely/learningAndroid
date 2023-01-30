package com.example.oopdemo

import android.util.Log

open class Car {
    var maxSpeed = 30

    open fun start() {
        Log.i("tag", "Car is starting...")
        Log.i("tag", "Max speed is $maxSpeed")
    }
}