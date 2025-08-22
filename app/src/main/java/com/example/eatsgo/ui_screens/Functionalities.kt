package com.example.eatsgo.ui_screens

import android.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import java.util.Calendar


@Composable
fun inputTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = Brown
    )
}


@Stable
class SenhaOutputTransformation : OutputTransformation {
    companion object {
        private val tag = SenhaOutputTransformation::class.simpleName
        private const val MASCARA_INTERNA = '\u2022'
    }
    override fun TextFieldBuffer.transformOutput() {
        val maskedText = AnnotatedString(MASCARA_INTERNA.toString().repeat(asCharSequence().length))
        replace(0, length, maskedText)
    }
}

@Composable
fun RatingBar(
    rating: Double,
    maxStars: Int = 5,
) {
    Row() {
        for (i in 1..maxStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star $i",
                tint = if (i <= rating) OrangeBase else Orange2,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


data class GreetingWithTagline(
    val headline: String,
    val tagline: String
)

fun getGreetingAndTagline(): GreetingWithTagline {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    return when (hour) {
        in 5..11 -> GreetingWithTagline(
            headline = "Good Morning",
            tagline = "Rise and shine! It's breakfast time"
        )

        in 12..16 -> GreetingWithTagline(
            headline = "Good Afternoon",
            tagline = "Midday fuel, keep it cool — it’s lunchtime"
        )

        in 17..20 -> GreetingWithTagline(
            headline = "Good Evening",
            tagline = "End the day the delicious way — it’s dinnertime"
        )

        else -> GreetingWithTagline(
            headline = "Good Night",
            tagline = "Late night cravings? Grab a snack"
        )
    }
}



