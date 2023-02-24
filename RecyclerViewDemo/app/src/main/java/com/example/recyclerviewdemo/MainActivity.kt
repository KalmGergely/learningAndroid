package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val fruitList = listOf<Fruit>(
        Fruit("Mango", "Joe"),
        Fruit("Banana", "Mike"),
        Fruit("Apple", "Jeff"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(
            fruitList,
        ) { selectedItem: Fruit ->
            listItemClick(selectedItem)
        }
    }

    private fun listItemClick(fruit: Fruit) {
        Toast.makeText(
            this@MainActivity,
            "Supplier: ${fruit.supplier}",
            Toast.LENGTH_LONG
        ).show()
    }
}