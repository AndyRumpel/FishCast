package com.arsoft.fishcast.utils

import java.text.NumberFormat

class TextConverter {
    companion object {
        fun deleteDigitsAfterDots(temperature: Double): String {
            val numberFormatter = NumberFormat.getInstance()
            numberFormatter.maximumFractionDigits = 0
            return numberFormatter.format(temperature)
        }
    }
}