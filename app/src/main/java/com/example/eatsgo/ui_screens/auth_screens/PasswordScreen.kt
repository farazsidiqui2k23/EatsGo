@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens.auth_screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui_screens.MainUILayout
import com.example.eatsgo.ui_screens.SenhaOutputTransformation
import com.example.eatsgo.ui_screens.inputTitle

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForgetPasswordScreen(
    context: Context,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MainUILayout(
        modifier = modifier,
        title = "Set Password",
        changeTitle = "Set Password",
        content = { PasswordCardLayout(navController, modifier) }
    ) {
        //back btn functionality
        Toast.makeText(context, "button prssed", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun PasswordCardLayout(navController: NavController, modifier: Modifier = Modifier) {


    var password = rememberTextFieldState()
    var confirm_password = rememberTextFieldState()

    var heading by rememberSaveable { mutableStateOf("Set Password") }

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


    Card(
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(modifier.padding(32.dp, 0.dp)) {


            Spacer(modifier = Modifier.height(12.dp))

            inputTitle("Password")
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

            Spacer(modifier = Modifier.height(16.dp))

            inputTitle("Confirm Password")
            BasicTextField(
                modifier = input_modifier,
                state = confirm_password,
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
                )
            )

            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier
                        .height(50.dp),
                    onClick = {

                    }, colors = ButtonColors(
                        containerColor = OrangeBase, contentColor = Cream,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        "Confirm New Password",
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}