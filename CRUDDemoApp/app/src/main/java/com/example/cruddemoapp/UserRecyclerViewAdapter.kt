package com.example.cruddemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cruddemoapp.db.User

class UserRecyclerViewAdapter()
    : RecyclerView.Adapter<UserViewHolder>() {
    private val userList = ArrayList<User>();

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)

        return UserViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class UserViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
    fun bind(user: User) {
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        tvName.text = user.name
        tvEmail.text = user.email
    }
}