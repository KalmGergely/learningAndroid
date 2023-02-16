package com.example.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.sbClap)
        handler = Handler(Looper.getMainLooper())
        val playPause = findViewById<FloatingActionButton>(R.id.fabPlayPause)
        var isPlaying = false;
        val stop = findViewById<FloatingActionButton>(R.id.fabStop)

        playPause.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.clap)
            }
            isPlaying = if (!isPlaying) {
                mediaPlayer?.start()
                playPause.setImageResource(R.drawable.ic_pause)
                initializeSeekBar()
                true
            } else {
                mediaPlayer?.pause()
                playPause.setImageResource(R.drawable.ic_play)
                false
            }
        }

        stop.setOnClickListener {
            isPlaying = false
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            playPause.setImageResource(R.drawable.ic_play)
            handler.removeCallbacks(runnable)
            seekBar.progress = 0
        }
    }

    private fun initializeSeekBar() {
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if(fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        val tvPlayed = findViewById<TextView>(R.id.tvPlayed)
        val tvDue = findViewById<TextView>(R.id.tvDue)
        seekBar.max = mediaPlayer!!.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentPosition
            handler.postDelayed(runnable, 500)
            val playedTime = mediaPlayer!!.currentPosition/1000
            tvPlayed.text = "$playedTime sec"
            val dueTime = mediaPlayer!!.duration/1000 - playedTime
            tvDue.text = "$dueTime sec"
        }
        handler.postDelayed(runnable, 500)
    }
}