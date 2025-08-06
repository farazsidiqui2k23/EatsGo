package com.example.eatsgo.ui_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2

@Composable
fun BoardingScr(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange2)
    ) {

        Image(
            painter = painterResource(id = R.drawable.foodorderguy),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(0.dp, 160.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .fillMaxHeight(.5f)
        )

        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomEnd) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.4f)
                ) {}

            }
        }
    }
}

fun



@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun Ui() {
    BoardingScr()
}