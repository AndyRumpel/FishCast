package com.arsoft.fishcast.utils

import android.annotation.SuppressLint
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MyDateTimeFormatter {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun timeFormat(time: Long): String {
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val convertedTime = Time(time)
            return simpleDateFormat.format(convertedTime)
        }

        @SuppressLint("SimpleDateFormat")
        fun dateFormat(date: Long): String {
            val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val convertedDate = Date(date)
            return simpleDateFormat.format(convertedDate)
        }
    }
}