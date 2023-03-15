package com.example.cruddemoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cruddemoapp.db.User

@Dao
interface UserDao {
    @Insert
    suspend fun createUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun indexUsers(): LiveData<List<User>>
}