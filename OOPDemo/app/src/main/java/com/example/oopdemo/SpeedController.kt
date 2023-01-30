package com.example.oopdemo

interface SpeedController {
    fun accelerate()
    fun decelerate()

    fun getBrandId(): String {
        return "BrandIdPlaceHolder"
    }
}