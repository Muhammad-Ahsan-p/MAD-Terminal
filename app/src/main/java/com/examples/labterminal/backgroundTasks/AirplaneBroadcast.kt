package com.examples.labterminal.backgroundTasks

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneBroadcast: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0,"Airplane mode is toggled..." , Toast.LENGTH_LONG).show()
    }
}