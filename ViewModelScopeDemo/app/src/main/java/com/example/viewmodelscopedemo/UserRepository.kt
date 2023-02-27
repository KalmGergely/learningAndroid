package com.example.viewmodelscopedemo

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8000)

        return listOf(
            User(1, "Betti"),
            User(2, "Geri")
        )
    }
}