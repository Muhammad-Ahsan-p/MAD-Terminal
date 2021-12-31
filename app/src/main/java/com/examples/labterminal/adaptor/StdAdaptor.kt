package com.examples.labterminal.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.labterminal.R
import com.examples.labterminal.model.Student

class StdAdaptor(val student:ArrayList<Student>):RecyclerView.Adapter<StdAdaptor.StudentHolder>() {

    private lateinit var handleSend:onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        handleSend = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_component, parent, false)
        return StudentHolder(view, handleSend)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.name.text = student[position].name.toString()
        holder.regNo.text = student[position].regNo.toString()
        holder.phoneNo.text = student[position].phoneNo.toString()
    }

    override fun getItemCount(): Int {
        return student.size
    }

    class StudentHolder(view: View, listener: StdAdaptor.onItemClickListener):RecyclerView.ViewHolder(view){
        val btnSend:Button
        val name:TextView
        val regNo:TextView
        val phoneNo:TextView

        init {
            name =view.findViewById(R.id.txt_name)
            regNo =view.findViewById(R.id.txt_reg)
            phoneNo =view.findViewById(R.id.txt_phone)
            btnSend = view.findViewById(R.id.btn_sms)
            btnSend.setOnClickListener { listener.onItemClick(adapterPosition) }
        }

    }
}