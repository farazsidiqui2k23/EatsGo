@file:OptIn(ExperimentalFoundationApi::class)

package com.example.eatsgo.ui_screens.auth_screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUp_Scr(modifier: Modifier = Modifier) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var full_name = rememberTextFieldState()
    var mobile_no by rememberSaveable { mutableStateOf("") }
    var date_of_birth by rememberSaveable { mutableStateOf("") }

    var heading by rememberSaveable { mutableStateOf("Sign Up") }

    var pass_visibility by rememberSaveable { mutableStateOf(false) }

    val input_modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 2.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Yellow2)
        .padding(12.dp)

    val composableScope = rememberCoroutineScope()
    composableScope.launch {
        delay(2000)
        heading = "New Account"
    }

    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase)
    ) {
        Column(modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.15f)
                    .padding(12.dp, 12.dp, 12.dp, 32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp),
                        tint = OrangeBase
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = heading,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 26.sp,
                        color = Cream
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(Cream),
                shape = RoundedCornerShape(20.dp, 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(modifier.padding(32.dp, 0.dp)) {


                    Text(
                        text = "Full Name",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )
                    BasicTextField2(
                          modifier = input_modifier,
                        state = full_name,
                        cursorBrush = SolidColor(OrangeBase),
                        textStyle = TextStyle(
                            color = Brown, fontSize = 16.sp
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine,
                        keyboardActions =


                        )

//                    Text(
//                        text = "Email",
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                        color = Brown
//                    )
//
//                    TextField(
//                        modifier = Modifier
//                            .height(50.dp)
//                            .fillMaxWidth(),
//                        value = email,
//                        onValueChange = {
//                            email = it
//                        },
//                        singleLine = true,
//                        shape = RoundedCornerShape(18.dp),
//                        colors = TextFieldDefaults.colors(
//                            focusedTextColor = Brown,
//                            focusedContainerColor = Yellow2,
//                            unfocusedContainerColor = Yellow2,
//                            cursorColor = OrangeBase,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//
//
//                    Text(
//                        text = "Password",
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                        color = Brown
//                    )
//                    TextField(
//                        modifier = Modifier
//                            .height(50.dp)
//                            .fillMaxWidth(),
//
//                        trailingIcon = @Composable {
//                            IconButton(onClick = { pass_visibility = !pass_visibility }) {
//                                Icon(
//                                    if (pass_visibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
//                                    contentDescription = "password visibility"
//                                )
//                            }
//                        },
//
//                        value = password,
//                        onValueChange = {
//                            password = it
//                        },
//                        visualTransformation = if (pass_visibility) VisualTransformation.None  else PasswordVisualTransformation(),
//                        singleLine = true,
//                        shape = RoundedCornerShape(18.dp),
//                        colors = TextFieldDefaults.colors(
//                            focusedTextColor = Brown,
//                            focusedContainerColor = Yellow2,
//                            unfocusedContainerColor = Yellow2,
//                            cursorColor = OrangeBase,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//
//                    Text(
//                        text = "Mobile Number",
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                        color = Brown
//                    )
//
//                    TextField(
//                        modifier = Modifier
//                            .height(50.dp)
//                            .fillMaxWidth(),
//                        value = mobile_no,
//                        onValueChange = {
//                            mobile_no = it
//                        },
//                        singleLine = true,
//                        shape = RoundedCornerShape(18.dp),
//                        colors = TextFieldDefaults.colors(
//                            focusedTextColor = Brown,
//                            focusedContainerColor = Yellow2,
//                            unfocusedContainerColor = Yellow2,
//                            cursorColor = OrangeBase,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//
//                    Text(
//                        text = "Date of Birth",
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                        color = Brown
//                    )
//                    TextField(
//                        modifier = Modifier
//                            .height(50.dp)
//                            .fillMaxWidth(),
//                        value = date_of_birth,
//                        onValueChange = {
//                            date_of_birth = it
//                        },
//                        singleLine = true,
//                        shape = RoundedCornerShape(18.dp),
//                        colors = TextFieldDefaults.colors(
//                            focusedTextColor = Brown,
//                            focusedContainerColor = Yellow2,
//                            unfocusedContainerColor = Yellow2,
//                            cursorColor = OrangeBase,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )

//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth(), contentAlignment = Alignment.TopEnd
//                    ) {
//                        Column {
//                        Text(
//                            text = "By continuing, you agree to",
//                            fontSize = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)), color = OrangeBase
//                        )
//                            Row {
//                                Text(
//                                    text = "Terms of use",
//                                    fontSize = 16.sp,
//                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                                    color = OrangeBase
//                                )
//                                Text(
//                                    text = "&",
//                                    fontSize = 16.sp,
//                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                                    color = OrangeBase
//                                )
//                                Text(
//                                    text = "Privacy Policy.",
//                                    fontSize = 16.sp,
//                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                                    color = OrangeBase
//                                )
//                            }
//                        }
//                    }


//                    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Button(
//                                modifier = Modifier
//                                    .width(250.dp)
//                                    .height(50.dp),
//                                onClick = {}, colors = ButtonColors(
//                                    containerColor = OrangeBase, contentColor = Cream,
//                                    disabledContainerColor = Color.White,
//                                    disabledContentColor = Color.White
//                                )
//                            ) {
//                                Text(
//                                    "Log In",
//                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                                    fontSize = 22.sp
//                                )
//                            }
//
//                            Text(text = "or", color = Brown, modifier = Modifier.padding(0.dp, 20.dp))
//
//                            Box(
//                                modifier = Modifier
//                                    .background(
//                                        color = Orange2,
//                                        shape = RoundedCornerShape(20.dp)
//                                    )
//                            ) {
//                                IconButton(onClick = {}) {
//                                    Icon(
//                                        Icons.Default.Fingerprint,
//                                        contentDescription = "Fingerprint",
//                                        modifier = Modifier.size(60.dp),
//                                        tint = OrangeBase
//
//                                    )
//                                }
//
//                            }
//                        }
//                    }
//
//                    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                        Text(
//                            text = "Don't have an account? ",
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                            color = Brown,
//                            fontWeight = FontWeight.Thin
//                        )
//                        Text(
//                            text = "Sign Up",
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                            color = OrangeBase,
//                            fontWeight = FontWeight.Thin,
//
//                            )
//                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun Ui() {
    SignUp_Scr()
}