package com.example.myagenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarPagerAdapter(private val context: MainActivity) : RecyclerView.Adapter<CalendarPagerAdapter.CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_page, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - 500)
        holder.bind(calendar)
    }

    override fun getItemCount(): Int = 1000

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        fun bind(calendar: Calendar) {
            val days = mutableListOf<String>()
            val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            for (i in 1..maxDay) days.add(i.toString())
            recyclerView.layoutManager = GridLayoutManager(context, 7)
            recyclerView.adapter = CalendarAdapter(context, days)
        }
    }
}