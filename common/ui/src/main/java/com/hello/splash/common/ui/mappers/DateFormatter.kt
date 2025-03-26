package com.hello.splash.common.ui.mappers

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    private val inputFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        }
    }

    private val outputFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("dd/MM/yy, hh:mm a", Locale.getDefault()).apply {
                timeZone = TimeZone.getDefault()
            }
        }
    }

    fun format(unformatted: String): String {
        if (unformatted.isEmpty()) return ""

        return try {
            val date = inputFormat.get().parse(unformatted) ?: return ""
            outputFormat.get().format(date)
        } catch (_: Exception) {
            ""
        }
    }
}