package com.example.eatsgo.ui_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase

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
                .padding(0.dp, 130.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .fillMaxHeight(.45f)
        )

        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 30.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Button(
                    onClick = {}, colors = ButtonColors(
                        containerColor = OrangeBase, contentColor = Cream,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                    )
                ) { Text("Skip", fontSize = 14.sp) }
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomEnd) {
                BoardingMenu()

            }
        }
    }
}

@Composable
fun BoardingMenu() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.48f),
        colors = CardDefaults.cardColors(Cream), shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "",
                modifier = Modifier.size(60.dp), tint = OrangeBase
            )
            Text(
                text = "Order Food",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 26.sp, color = OrangeBase
            )
            Text(
                text = "Heart-healthy meals delivered by hand to your house.",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp, color = Brown, textAlign = TextAlign.Center
            )

            Row(modifier = Modifier.padding(0.dp, 14.dp)) {

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(8.dp)
                            .width(24.dp)
                            .clip(CircleShape)
                            .background(OrangeBase)
                    )

            }

            Button(
                onClick = {}, colors = ButtonColors(
                    containerColor = OrangeBase,
                    contentColor = Cream,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                ), modifier = Modifier.fillMaxWidth(.4f)
            ) {
                Text("Next", fontSize = 14.sp)
            }

        }
    }

}


@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun Ui() {
    BoardingScr()
}