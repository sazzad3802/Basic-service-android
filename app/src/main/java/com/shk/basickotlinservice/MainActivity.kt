package com.shk.basickotlinservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSTartService: Button = findViewById(R.id.buttonStart)
        val btnStopService: Button = findViewById(R.id.buttonStop)
        val buttonSendData: Button = findViewById(R.id.buttonSendData)
        val tvServiceStatus: TextView = findViewById(R.id.tvServiceStatus)
        val etSendData: EditText = findViewById(R.id.etSendData)

        btnSTartService.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                startService(it)
                tvServiceStatus.text = "Service running..."
            }
        }

        btnStopService.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                stopService(it)
                tvServiceStatus.text = "Service stopped..."
            }
        }

        buttonSendData.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                val ds = etSendData.text.toString()
                it.putExtra("EXTRA_DATA", ds)
                startService(it)
                tvServiceStatus.text = "Service stopped..."
            }
        }
    }
}