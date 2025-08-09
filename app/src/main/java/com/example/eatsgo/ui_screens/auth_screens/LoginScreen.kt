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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.CodepointTransformation
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.mask
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun Login_Scr(modifier: Modifier = Modifier) {

    var email = rememberTextFieldState()
    var password = rememberTextFieldState()

    var heading by rememberSaveable { mutableStateOf("Log In") }

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
        heading = "Hello"
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
                        text = "Welcome",
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Brown
                    )
                    Spacer(modifier = Modifier.height(12.dp))
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
                        lineLimits = TextFieldLineLimits.SingleLine,
                        decorator = { innerTextField ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(modifier = Modifier.weight(1f)) {
                                    innerTextField()
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                IconButton(
                                    onClick = { passVisibility = !passVisibility },
                                    modifier = Modifier
                                        .then(Modifier.size(24.dp))
                                ) {
                                    Icon(
                                        if (passVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "",
                                    )
                                }

                            }
                        },
                        codepointTransformation = if (!passVisibility) CodepointTransformation.mask(
                            '\u25CF'
                        ) else null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(), contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "Forget Password",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)), color = OrangeBase
                        )
                    }

                    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                Button(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(220.dp),
                                    onClick = {}, colors = ButtonColors(
                                        containerColor = OrangeBase, contentColor = Cream,
                                        disabledContainerColor = Color.White,
                                        disabledContentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        "Log In",
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        fontSize = 20.sp
                                    )
                                }
                            }

                            Text(text = "or", color = Brown, modifier = Modifier.padding(0.dp, 20.dp))

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Orange2,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            ) {
                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Fingerprint,
                                        contentDescription = "Fingerprint",
                                        modifier = Modifier.size(60.dp),
                                        tint = OrangeBase

                                    )
                                }

                            }
                        }
                    }

                    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "Don't have an account? ",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Brown,
                            fontWeight = FontWeight.Thin
                        )
                        Text(
                            text = "Sign Up",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = OrangeBase,
                            fontWeight = FontWeight.Thin,

                            )
                    }
                }
            }
        }
    }

}

//@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
//@Composable
//fun Ui() {
//    Login_Scr()
//}