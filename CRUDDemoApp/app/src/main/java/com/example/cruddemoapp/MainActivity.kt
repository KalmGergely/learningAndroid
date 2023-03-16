package com.example.cruddemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cruddemoapp.db.AppDB
import com.example.cruddemoapp.db.User

class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button

    private lateinit var viewModel: UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)

        userRecyclerView = findViewById(R.id.rvUser)

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

        initRecyclerView()
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

    private fun initRecyclerView() {
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserRecyclerViewAdapter()
        userRecyclerView.adapter = adapter
        displayUsers()
    }

    private fun displayUsers() {
        viewModel.users.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }
}