package com.example.cruddemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cruddemoapp.databinding.ActivityMainBinding
import com.example.cruddemoapp.db.AppDB
import com.example.cruddemoapp.db.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserRecyclerViewAdapter

    private lateinit var selectedUser: User
    private var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = AppDB.getInstance(application).userDao()
        val factory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.apply {
            btnSave.setOnClickListener() {
                if (isListItemClicked) {
                    updateUserData()
                    clearUserData()
                } else {
                    saveUserData()
                    clearUserData()
                }
            }

            btnClear.setOnClickListener() {
                if (isListItemClicked) {
                    deleteUser()
                }
                clearUserData()
            }

            initRecyclerView()
        }
    }

    private fun saveUserData() {
        binding.apply {
            viewModel.createUser(
                User(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }
    }

    private fun clearUserData() {
        binding.apply {
            etName.text.clear()
            etEmail.text.clear()
        }
    }

    private fun updateUserData() {
        binding.apply {
            viewModel.updateUser(
                User(
                    selectedUser.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )

            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun deleteUser() {
        binding.apply {
            viewModel.deleteUser(selectedUser)

            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun initRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserRecyclerViewAdapter { selectedItem: User ->
            listItemClicked(selectedItem)
        }
        binding.rvUser.adapter = adapter
        displayUsers()
    }

    private fun displayUsers() {
        viewModel.users.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(user: User) {
        binding.apply {
            selectedUser = user
            etName.setText(selectedUser.name)
            etEmail.setText(selectedUser.email)

            btnSave.text = "Update"
            btnClear.text = "Delete"

            isListItemClicked = true
        }
    }
}