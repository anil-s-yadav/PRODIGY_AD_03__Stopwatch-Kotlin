package com.example.stopwatchkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button

    private var startTime: Long = 0
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById(R.id.tvTimer)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)


        btnStart.setOnClickListener {
            startTimer()
        }

        btnStop.setOnClickListener {
            stopTimer()
        }

        btnReset.setOnClickListener {
            stopTimer()
            tvTimer.setText("00:00:00:00")
         }
    }

    private fun startTimer() {
        btnStart.isEnabled = false
        btnStop.isEnabled = true
        startTime = SystemClock.uptimeMillis()
        handler.postDelayed(runnable, 0)
    }

    private fun stopTimer() {
        btnStart.isEnabled = true
        btnStop.isEnabled = false
        handler.removeCallbacks(runnable)
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            val currentTime = SystemClock.uptimeMillis() - startTime
            val milliseconds = currentTime % 1000
            val seconds = (currentTime / 1000).toInt()
            val minutes = seconds / 60
            val hours = minutes / 60

            val formattedTime =
                String.format("%02d:%02d:%02d.%03d", hours, minutes % 60, seconds % 60, milliseconds)

            tvTimer.text = formattedTime

            handler.postDelayed(this, 10) // Update every 10 milliseconds for better accuracy
        }
    }
}