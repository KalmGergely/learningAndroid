package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.etWeight)
        val heightInput = findViewById<EditText>(R.id.etHeight)
        val calcBtn = findViewById<Button>(R.id.btnCalculate)

        calcBtn.setOnClickListener {
            val weight = weightInput.text.toString()
            val height = heightInput.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult (bmi: Float) {
        val bmiOutput = findViewById<TextView>(R.id.tvIndex)
        val bmiStatement = findViewById<TextView>(R.id.tvStatement)
        val bmiInfo = findViewById<TextView>(R.id.tvInfo)

        bmiOutput.text = bmi.toString()
        bmiInfo.text = "(Healthy range is 18.5 - 24.9)"

        var statement = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                statement = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.50 .. 24.99 -> {
                statement = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00 .. 29.99 -> {
                statement = "Overweight"
                color = R.color.overweight
            }
            bmi > 29.99 -> {
                statement = "Obese"
                color = R.color.obese
            }
        }

        bmiStatement.setTextColor(ContextCompat.getColor(this, color))
        bmiStatement.text = statement
    }
}