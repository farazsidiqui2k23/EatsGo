@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens.auth_screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.Firebase.AuthState
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import com.example.eatsgo.ui_screens.MainUILayout
import com.example.eatsgo.ui_screens.SenhaOutputTransformation

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUpScreen(authViewModel: AuthViewModel, context: Context, navController: NavController, modifier: Modifier = Modifier) {

    MainUILayout(
        modifier = modifier,
        title = "Sign Up",
        changeTitle = "New Account",
        content = { SignUpCardLayout(context, authViewModel, navController, modifier) }
    ) {
        //onback functionality
        Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SignUpCardLayout(context: Context, authViewModel: AuthViewModel, navController: NavController, modifier: Modifier = Modifier) {

    var email = rememberTextFieldState()
    var password = rememberTextFieldState()
    var full_name = rememberTextFieldState()
    var mobile_no = rememberTextFieldState()
    var date_of_birth = rememberTextFieldState()

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

    val userState = authViewModel.authState.observeAsState()
    LaunchedEffect(userState.value){
        when (val state = userState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("LogInScreen")
            }
            is AuthState.onFailure -> {
                Toast.makeText(context, state.message.getContentIfNotObserved(), Toast.LENGTH_SHORT).show()
            }
            else ->{
                Unit
            }
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
            BasicTextField(
                modifier = input_modifier,
                state = full_name,
                cursorBrush = SolidColor(OrangeBase),
                textStyle = TextStyle(
                    color = Brown, fontSize = 16.sp
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Text(
                text = "Email",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Brown
            )


            BasicTextField(
                modifier = input_modifier,
                state = email,
                cursorBrush = SolidColor(OrangeBase),
                textStyle = TextStyle(
                    color = Brown, fontSize = 16.sp
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )


            Text(
                text = "Password",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Brown
            )
            BasicTextField(
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
                                contentDescription = "", tint = OrangeBase
                            )
                        }

                    }
                },
                outputTransformation = if (!passVisibility) {
                    SenhaOutputTransformation()
                } else {
                    null
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )

            )

            Text(
                text = "Mobile Number",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Brown
            )

            BasicTextField(
                modifier = input_modifier,
                state = mobile_no,
                cursorBrush = SolidColor(OrangeBase),
                textStyle = TextStyle(
                    color = Brown, fontSize = 16.sp
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Text(
                text = "Date of Birth",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Brown
            )
            BasicTextField(
                modifier = input_modifier,
                state = date_of_birth,
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
                            if (date_of_birth.text.isEmpty()) {
                                Text("dd/mm/yyyy", color = Brown.copy(alpha = 0.5f))
                            } else {
                                innerTextField()
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        IconButton(
                            onClick = {
                                //dob icon functionality
                            },
                            modifier = Modifier
                                .then(Modifier.size(24.dp))
                        ) {
                            Icon(
                                Icons.Default.CalendarMonth,
                                contentDescription = "Calender Icon",
                                tint = OrangeBase
                            )
                        }

                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )

            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(x = 0.dp, y = 8.dp)
                ) {
                    Text(
                        text = "By continuing, you agree to",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Brown, modifier = Modifier.offset(x = 0.dp, y = 8.dp)
                    )
                    Row {
                        Text(
                            text = "Terms of use",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = OrangeBase
                        )
                        Text(
                            text = " and ",
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
                        .height(50.dp),
                    onClick = {

                        authViewModel.UserSignUp(
                            email = email.text.toString(),
                            password = password.text.toString(),
                            name = full_name.text.toString(),
                            contact = mobile_no.text.toString(),
                            dob = date_of_birth.text.toString()
                        )

                    }, colors = ButtonColors(
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Brown,
                )
                Text(
                    text = "Log In",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = OrangeBase,

                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = { navController.navigate("LogInScreen") })
                )
            }
        }
    }
}