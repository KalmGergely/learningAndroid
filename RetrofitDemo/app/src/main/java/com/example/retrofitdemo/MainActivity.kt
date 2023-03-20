package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData: LiveData<Response<Albums>> = liveData {
            emit(retService.getAlbums())
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()

            if(albumList != null)
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    Log.i("Album items", albumItem.title)
                }
        })
    }
}