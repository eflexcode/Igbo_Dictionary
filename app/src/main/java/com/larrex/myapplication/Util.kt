package com.larrex.myapplication

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {

        val quicksand = FontFamily(

            Font(R.font.quicksand_regular, FontWeight.Normal),
            Font(R.font.quicksand_medium, FontWeight.Medium),
            Font(R.font.quicksand_bold, FontWeight.Bold)

        )

        fun getGreeting(): String {

            val time = Date().hours

            return if (time < 12) {
                "Good morning"
            } else if (time < 16) {
                "Good afternoon"
            } else if (time < 18) {
                "Good evening"
            } else {
                "Good night"
            }

        }

        fun formatDate(oldDate: String): String {


            val format = SimpleDateFormat("yyyy-MM-dd")
            val date = format.parse(oldDate)
            return format
                .format(date)
        }
    }

}