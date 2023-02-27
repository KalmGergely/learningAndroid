package com.example.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    private var userRepository : UserRepository = UserRepository()
    var listOfUsers: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            var result : List<User>? = null
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()

            }
            listOfUsers.value = result
        }
        println(listOfUsers)
    }
}