package com.shk.basickotlinservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var count: Int = 0
    var isServiceBound = false
    var mService: BasicService? = null
    var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSTartService: Button = findViewById(R.id.buttonStart)
        val btnStopService: Button = findViewById(R.id.buttonStop)
        val buttonBind: Button = findViewById(R.id.buttonBind)
        val buttonUnbind: Button = findViewById(R.id.buttonUnbind)
        val buttonRandom: Button = findViewById(R.id.buttonRandom)
        val tvServiceStatus: TextView = findViewById(R.id.tvServiceStatus)
        val etSendData: EditText = findViewById(R.id.etSendData)

        Toast.makeText(applicationContext, "thread id -> ${Thread.currentThread().id}", Toast.LENGTH_SHORT).show()


        btnSTartService.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                startService(it)
                tvServiceStatus.text = "Service started..."
            }
        }

        btnStopService.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                stopService(it)
                tvServiceStatus.text = "Service stopped..."
            }
        }

        buttonBind.setOnClickListener {
            Intent(this, BasicService::class.java).also {
                if(serviceConnection == null){
                    serviceConnection = object : ServiceConnection {
                        override fun onServiceConnected(name: ComponentName?, serviceBinder: IBinder?) {
                            val binder = serviceBinder as BasicService.MyServiceBinder
                            mService = binder.getService()
                            isServiceBound = true
                            tvServiceStatus.text = "Service connected..."
                        }

                        override fun onServiceDisconnected(name: ComponentName?) {
                            mService = null
                            isServiceBound = false
                            tvServiceStatus.text = "Service disconnected..."
                        }
                    }
                }
                bindService(it, serviceConnection!!, Context.BIND_AUTO_CREATE)
                tvServiceStatus.text = "Service bounded..."
            }
        }


        buttonUnbind.setOnClickListener {
            if(isServiceBound){
                unbindService(serviceConnection!!)
                isServiceBound = false
                tvServiceStatus.text = "Service unbounded..."
            }
        }

        buttonRandom.setOnClickListener {
            if(isServiceBound){
                tvServiceStatus.text = mService?.randomNumber.toString()
            }
        }
    }
}