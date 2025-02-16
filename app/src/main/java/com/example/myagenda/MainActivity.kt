package com.example.myagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var monthTextView: TextView
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthTextView = findViewById(R.id.monthTextView)
        viewPager = findViewById(R.id.viewPager)

        val adapter = CalendarPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(500, false)

        updateMonthTitle(500)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateMonthTitle(position)
            }
        })
    }

    private fun updateMonthTitle(position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - 500)
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        monthTextView.text = dateFormat.format(calendar.time)
    }
}
