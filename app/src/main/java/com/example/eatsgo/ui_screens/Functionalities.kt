package com.example.eatsgo.ui_screens

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
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