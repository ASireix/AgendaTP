package com.example.myagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var monthTextView: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var eventEditText: EditText
    private val events = mutableMapOf<String, String>()
    private var selectedDay: String? = null
    private var selectedView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthTextView = findViewById(R.id.monthTextView)
        viewPager = findViewById(R.id.viewPager)
        eventEditText = findViewById(R.id.eventEditText)

        val adapter = CalendarPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(500, false)

        updateMonthTitle(500)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateMonthTitle(position)
                selectedDay = null
                selectedView = null
                eventEditText.setText("")
            }
        })
    }

    private fun updateMonthTitle(position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - 500)
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        monthTextView.text = dateFormat.format(calendar.time)
    }

    fun updateEvent(day: String, view: TextView) {
        saveEvent()
        selectedView?.let {
            if (hasEvent(selectedDay ?: "")) {
                it.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            } else {
                it.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            }
        }

        selectedDay = day
        selectedView = view
        selectedView?.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
        eventEditText.setText(events[day] ?: "")
    }

    fun saveEvent() {
        selectedDay?.let { day ->
            events[day] = eventEditText.text.toString()
            //Toast.makeText(this, "Événement sauvegardé", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteEvent(day: String) {
        events.remove(day)
        if (selectedDay == day) {
            eventEditText.setText("")
            selectedView?.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
            selectedDay = null
            selectedView = null
        }
    }

    fun hasEvent(day: String): Boolean {
        return events[day]?.isNotEmpty() == true
    }
}
