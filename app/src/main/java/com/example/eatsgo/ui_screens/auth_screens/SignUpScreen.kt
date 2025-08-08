@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
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

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUp_Scr(modifier: Modifier = Modifier) {

    var email = rememberTextFieldState()
    var password = rememberTextFieldState()
    var full_name = rememberTextFieldState()
    var mobile_no = rememberTextFieldState()
    var date_of_birth = rememberTextFieldState()

    var heading by rememberSaveable { mutableStateOf("Sign Up") }

    var passVisibility by rememberSaveable { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val isKeyboardVisible = WindowInsets.isImeVisible

    val input_modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 2.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Yellow2)
        .padding(12.dp)
        .focusRequester(focusRequester)
        .clickable {
            if (!isKeyboardVisible) {
                focusRequester.requestFocus()
            }
        }

    LaunchedEffect(isKeyboardVisible) {
        if (!isKeyboardVisible) {
            focusManager.clearFocus()
        }
    }

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
                        lineLimits = TextFieldLineLimits.SingleLine
                    )

                    Text(
                        text = "Email",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )

                    BasicTextField2(
                        modifier = input_modifier,
                        state = email,
                        cursorBrush = SolidColor(OrangeBase),
                        textStyle = TextStyle(
                            color = Brown, fontSize = 16.sp
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine
                    )


                    Text(
                        text = "Password",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )
                    BasicTextField2(
                        modifier = input_modifier,
                    state = password,
                        cursorBrush = SolidColor(OrangeBase),
                        textStyle = TextStyle(
                            color = Brown, fontSize = 16.sp
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine
                    )

                    Text(
                        text = "Mobile Number",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )

                    BasicTextField2(
                        modifier = input_modifier,
                        state =mobile_no,
                        cursorBrush = SolidColor(OrangeBase),
                        textStyle = TextStyle(
                            color = Brown, fontSize = 16.sp
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine
                    )

                    Text(
                        text = "Date of Birth",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )
                    BasicTextField2(
                        modifier = input_modifier,
                        state = date_of_birth,
                        cursorBrush = SolidColor(OrangeBase),
                        textStyle = TextStyle(
                            color = Brown, fontSize = 16.sp
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column {
                                Text(
                                    text = "By continuing, you agree to",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Brown, modifier = Modifier.offset(x = 0.dp, y= 8.dp)
                                )
                                Row {
                                    Text(
                                        text = "Terms of use",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        color = OrangeBase
                                    )
                                    Text(
                                        text =  " and ",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        color = Brown
                                    )
                                    Text(
                                        text = "Privacy Policy.",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        color = OrangeBase
                                    )
                                }

                        }
                    }


                    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(50.dp),
                                onClick = {}, colors = ButtonColors(
                                    containerColor = OrangeBase, contentColor = Cream,
                                    disabledContainerColor = Color.White,
                                    disabledContentColor = Color.White
                                )
                            ) {
                                Text(
                                    "Create Account",
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 20.sp
                                )
                            }
                    }

                    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "Already have an account? ",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Brown,
                        )
                        Text(
                            text = "Sign In",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = OrangeBase,

                            )
                    }
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