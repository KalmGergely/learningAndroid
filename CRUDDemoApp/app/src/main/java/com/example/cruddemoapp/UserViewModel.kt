package com.example.cruddemoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cruddemoapp.db.User
import com.example.cruddemoapp.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val dao: UserDao) : ViewModel() {

    val users = dao.indexUsers()

    fun createUser(user: User)=viewModelScope.launch {
            dao.createUser(user)
    }

    fun updateUser(user: User)=viewModelScope.launch {
        dao.updateUser(user)
    }

    fun deleteUser(user: User)=viewModelScope.launch {
        dao.deleteUser(user)
    }

}