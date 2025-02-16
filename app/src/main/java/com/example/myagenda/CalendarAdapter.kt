package com.example.myagenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter(private val context: MainActivity, private val days: List<String>) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount(): Int = days.size

    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dayTextView: TextView = view.findViewById(R.id.dayTextView)

        fun bind(day: String) {
            dayTextView.text = day
            itemView.setOnClickListener {
                Toast.makeText(context, "Ajouter un événement pour le $day", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
