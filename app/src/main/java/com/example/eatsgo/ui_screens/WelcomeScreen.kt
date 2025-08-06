package com.example.eatsgo.ui_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun Welcome_scr(modifier: Modifier = Modifier) {

    Column(
        modifier
            .fillMaxSize()
            .background(OrangeBase),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Spacer(modifier.height(200.dp))
        Image(
            painter = painterResource(id = R.drawable.login_screen_logo),
            contentDescription = "logo", modifier = Modifier.size(202.dp, 179.dp)
        )
        Spacer(modifier= Modifier.height(25.dp))
        Text(
            text = "Hot Meals. Fast Wheels.\nDelivered Fresh.",
            color = Cream, textAlign = TextAlign.Center, fontSize = 18.sp
        )

        Spacer(modifier= Modifier.height(50.dp))

        Button(
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            onClick = {}, colors = ButtonColors(
                containerColor = YellowBase, contentColor = OrangeBase,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.White
            )
        ) { Text("Log In", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 22.sp) }

        Spacer(modifier =  Modifier.height(6.dp))

        Button(
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            onClick = {}, colors = ButtonColors(
                containerColor = Yellow2, contentColor = OrangeBase,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.White
            )
        ) { Text("Sign Up", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 22.sp) }

    }
}


//@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
//@Composable
//fun Ui() {
//    Welcome_scr()
//}