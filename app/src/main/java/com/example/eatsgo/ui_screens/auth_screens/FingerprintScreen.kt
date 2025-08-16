@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens.auth_screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui_screens.MainUILayout

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FingerprintScreen(context: Context,navController: NavController,modifier: Modifier = Modifier) {

    MainUILayout(
        modifier = modifier,
        title = "Set Fingerprint",
        changeTitle = "Fingerprint",
        content = { FingerprintCardLayout(navController, modifier) }
    ) {
        //back press functionality
        Toast.makeText(context, "back pressed", Toast.LENGTH_SHORT).show()
        navController.popBackStack()
    }



}

@Composable
fun FingerprintCardLayout(navController: NavController, modifier: Modifier = Modifier) {

    var fingerprintAuth by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier.padding(32.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.border(1.dp, Color.Red)) {
                Icon(
                    Icons.Default.Fingerprint,
                    contentDescription = "",
                    modifier.size(300.dp),
                    tint = if (fingerprintAuth) OrangeBase else Orange2
                )
            }

            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(130.dp),
                        onClick = {}, colors = ButtonColors(
                            containerColor = Orange2, contentColor = OrangeBase,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text(
                            "Skip",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp
                        )
                    }

                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(130.dp),
                        onClick = {}, colors = ButtonColors(
                            containerColor = OrangeBase, contentColor = Cream,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text(
                            if (fingerprintAuth) "Continue" else "Authorize",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
