package com.examples.labterminal.backgroundTasks

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService:Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Service started", "I am started...")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Service stopped", "I am stopped...")
    }

}