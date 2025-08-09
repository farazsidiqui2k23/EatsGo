package com.example.eatsgo.ui_screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown

class Functionalities() {

}

@Composable
fun inputTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = Brown
    )
}