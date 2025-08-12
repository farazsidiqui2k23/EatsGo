@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
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
import androidx.compose.ui.text.font.FontWeight
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
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui_screens.MainUILayout
import com.example.eatsgo.ui_screens.SenhaOutputTransformation

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LogInScreen(context: Context,authViewModel: AuthViewModel, navController: NavController, modifier: Modifier = Modifier) {

    MainUILayout(
        modifier = modifier,
        title = "Log In",
        changeTitle = "Hello",
        content = { LoginCardLayout(context, authViewModel, navController, modifier) }
    ) {
        //back press functionality
        Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun LoginCardLayout(context: Context, authViewModel: AuthViewModel, navController: NavController, modifier: Modifier = Modifier) {

    var email = rememberTextFieldState()
    var password = rememberTextFieldState()

    var passVisibility by rememberSaveable { mutableStateOf(false) }

    val userState = authViewModel.authState.observeAsState()
    LaunchedEffect(userState.value){
        when (val state = userState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("HomeScreen")
            }
            is AuthState.onFailure -> {
            Toast.makeText(context, state.message.getContentIfNotObserved(), Toast.LENGTH_SHORT).show()
            }
            else ->{
                Unit
            }
        }
    }

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
                password,
                input_modifier,
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
                    imeAction = ImeAction.Done
                ))
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
                            onClick = {
                                authViewModel.UserLogIn(
                                    email = email.text.toString(),
                                    password = password.text.toString()
                                )
                            }, colors = ButtonColors(
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

                    Text(
                        text = "or",
                        color = Brown,
                        modifier = Modifier.padding(0.dp, 20.dp)
                    )

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
                    modifier = Modifier.clickable(enabled = true, onClick = {navController.navigate("SignUpScreen")})
                    )
            }
        }
    }
}