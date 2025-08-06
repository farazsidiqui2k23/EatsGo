package com.example.eatsgo.ui_screens.auth_screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.YellowBase


@Composable
fun Login_Scr(modifier: Modifier = Modifier) {

    Box(modifier.fillMaxSize().background(YellowBase)) {
        Column(modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(.2f).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {}) { Icon(Icons.Default.ArrowBack, contentDescription = "", modifier = Modifier.size(30.dp)) }
                Text(text = "Log In", fontFamily = FontFamily(Font(R.font.poppins_medium)))
            }
            Card(colors = CardDefaults.cardColors(Cream), shape = RoundedCornerShape(16.dp, 16.dp), modifier = Modifier.fillMaxWidth().fillMaxHeight()) { Column { } }
        }
    }

}

@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun Ui() {
    Login_Scr()
}