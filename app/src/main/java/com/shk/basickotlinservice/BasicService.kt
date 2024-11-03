package com.shk.basickotlinservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.util.Random

class BasicService: Service() {

    val TAG = "Basiservice"

    var randomNumber : Int = 0
    var generatorOn : Boolean = false

    val minNum = 0
    val maxNum = 100

    private val binder = MyServiceBinder()

    init {
        Log.d(TAG, "Service is running: ")
    }

    inner class MyServiceBinder : Binder() {
        fun getService() : BasicService{
            return this@BasicService
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        stopNumberGenerator()
    }

    override fun onBind(mIntent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(mIntent: Intent?, flags: Int, startId: Int): Int {
        generatorOn = true
        Thread { startNumberGenerator() }.start()
        return START_STICKY
    }

    private fun startNumberGenerator() {
        while (generatorOn){
            try {
                Thread.sleep(1000)
                if(generatorOn){
                    randomNumber = Random().nextInt(maxNum) + minNum
                    Handler(Looper.getMainLooper()).post {
//                        Toast.makeText(applicationContext, "${Thread.currentThread().id} ----- $randomNumber", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: InterruptedException){
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(applicationContext, "Thread interrupted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun stopNumberGenerator() {
        generatorOn = false
        randomNumber = 0
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, "Thread stopped", Toast.LENGTH_SHORT).show()
        }
    }
}