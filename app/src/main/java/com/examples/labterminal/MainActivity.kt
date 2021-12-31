package com.examples.labterminal

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.examples.labterminal.adaptor.StdAdaptor
import com.examples.labterminal.model.Student
import com.examples.labterminal.backgroundTasks.AirplaneBroadcast

class MainActivity : AppCompatActivity() {
    private val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"

    private lateinit var studentList:RecyclerView
    private lateinit var stdAdaptor: StdAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentsList = ArrayList<Student>()

        // only for testing purpose
        studentsList.add(Student("ahsan", "fa18-bse-028", "1234"))

        studentList = findViewById(R.id.student_list)

        val queue = Volley.newRequestQueue(this)

        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            url,null,
            Response.Listener { res ->
                for(index in 0 until res.length()){
                    var jsonObj = res.getJSONObject(index)
                    studentsList.add(
                        Student(
                            jsonObj.getString("name"),
                            jsonObj.getString("registerationNumber"),
                            jsonObj.getString("phoneNumber")
                        )
                    )
                }
            },
            null
        )

        queue.add(jsonRequest)
        //airplane mode broadcast receiver
        enableAirplaneMode()

        //recycler view
        stdAdaptor = StdAdaptor(studentsList)
        studentList.adapter = stdAdaptor
        studentList.layoutManager = LinearLayoutManager(this)

        stdAdaptor.setOnItemClickListener(object:StdAdaptor.onItemClickListener{
            override fun onItemClick(position: Int) {
                val contact = studentsList[position].phoneNo.toString()
                sendMessage(contact, "Hi, it is a test message")
            }
        })
    }

    private fun enableAirplaneMode(){
        val airplaneReceiver = AirplaneBroadcast()
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(airplaneReceiver, intent)
    }

    private fun sendMessage(contact: String, message: String) {
        var smsManger = SmsManager.getDefault()
        smsManger.sendTextMessage(contact,
            "ME",
            message,
            null,
            null
        )
        Toast.makeText(this, "Sending....", Toast.LENGTH_LONG).show()
    }
}