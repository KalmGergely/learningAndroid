package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    private lateinit var tvItems: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvItems = findViewById<TextView>(R.id.tvItems)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        getAblbumsByUserId(3)
        logAlbumById(1)
    }

    private fun getAblbumsByUserId(id: Int) {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            emit(retService.getAlbumsByUser(id))
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()

            if(albumList != null)
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    val result = "Album title: ${albumItem.title}\nAlbum id: ${albumItem.id}\nAlbum userId: ${albumItem.userId}\n\n"
                    tvItems.append(result)
                }
        })
    }

    private fun logAlbumById(id: Int) {
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            emit(retService.getAlbumsById(id))
        }

        pathResponse.observe(this, Observer {
            val album: AlbumItem? = it.body()

            if(album != null) {
                val result =
                    "Album title: ${album.title}\nAlbum id: ${album.id}\nAlbum userId: ${album.userId}\n\n"
                Log.i("Pathvariable practice", result)
            }
        })
    }
}