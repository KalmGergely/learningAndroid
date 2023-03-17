package com.example.cruddemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cruddemoapp.databinding.ActivityMainBinding
import com.example.cruddemoapp.databinding.ListItemBinding
import com.example.cruddemoapp.db.User

class UserRecyclerViewAdapter(
    private val clickListener:(User) -> Unit
): RecyclerView.Adapter<UserViewHolder>() {
    private val userList = ArrayList<User>();

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class UserViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener:(User) -> Unit) {
        binding.apply {
            tvName.text = user.name
            tvEmail.text = user.email

            root.setOnClickListener() {
                clickListener(user)
            }
        }
    }
}