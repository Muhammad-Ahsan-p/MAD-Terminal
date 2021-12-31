package com.examples.labterminal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.examples.labterminal.databinding.ActivityServiceBinding
import com.examples.labterminal.backgroundTasks.TestService

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding:ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startService(Intent(this,TestService::class.java))
        }

        binding.btnStop.setOnClickListener {
            stopService(Intent(this,TestService::class.java))
        }
    }
}