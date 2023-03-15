package com.example.cruddemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.cruddemoapp.db.AppDB
import com.example.cruddemoapp.db.User

class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)

        val dao = AppDB.getInstance(application).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        btnSave.setOnClickListener() {
            saveUserData()
            clearUserData()
        }

        btnClear.setOnClickListener() {
            clearUserData()
        }
    }

    private fun saveUserData() {
        viewModel.createUser(User(
            0,
            etName.text.toString(),
            etEmail.text.toString()));
    }

    private fun clearUserData() {
        etName.text.clear()
        etEmail.text.clear()
    }
}