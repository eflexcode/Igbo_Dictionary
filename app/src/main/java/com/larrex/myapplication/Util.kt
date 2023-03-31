package com.larrex.myapplication

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {
        val IGBO_API_LINK_BASE_URL = "https://igboapi.com/api/v1/"

        val quicksand = FontFamily(

            Font(R.font.quicksand_regular, FontWeight.Normal),
            Font(R.font.quicksand_medium, FontWeight.Medium),
            Font(R.font.quicksand_bold, FontWeight.Bold)

        )

        val nsibidi  = FontFamily(
            Font(R.font.akagu2020_3)
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

        fun getWordClassType(type: String): String {

          return  when (type) {
                "ADJ" -> return "Adjective"
                "ADV" -> return "Adverb"
                "AV" -> return "Active verb"
                "MV" -> return "Medial verb"
                "PV" -> return "Passive verb"
                "CJN" -> return "Conjunction"
                "DEM" -> return "Demonstrative"
                "NM" -> return "Name"
                "NNC" -> return "Noun"
                "NNP" -> return "Proper noun"
                "CD" -> return "Number"
                "PREP" -> return "Preposition"
                "PRN" -> return "Pronoun"
                "FW" -> return "Foreign word"
                "QTF" -> return "Quantifier"
                "WH" -> return "Interrogative"
                "INTJ" -> return "Interjection"
                "ISUF" -> return "Inflectional suffix"
                "ESUF" -> return "Extensional suffix"
                "SYM" -> return "Punctuations"
              else -> "Unknown"
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