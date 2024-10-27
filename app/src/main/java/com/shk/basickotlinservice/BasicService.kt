package com.shk.basickotlinservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class BasicService: Service() {

    val TAG = "Basiservice"

    init {
        Log.d(TAG, "Service is running: ")
    }

    override fun onBind(mIntent: Intent?): IBinder? = null

    override fun onStartCommand(mIntent: Intent?, flags: Int, startId: Int): Int {
        val dataString = mIntent?.getStringExtra("EXTRA_DATA")
         dataString?.let {
             Log.d(TAG, "onStartCommand: $it")
             Toast.makeText(this, "$dataString", Toast.LENGTH_SHORT).show()
         }
        return START_STICKY
    }
}