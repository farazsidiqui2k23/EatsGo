@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

package com.example.eatsgo.ui_screens.profile_drawer_screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui_screens.MainUILayout

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyProfileScreen(
    context: Context,
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    MainUILayout(
        modifier = modifier,
        title = "My Profile",
        changeTitle = "My Profile",
        content = { MyProfileCardLayout(context, authViewModel, navController, modifier) }
    ) {
        //back press functionality
        navController.popBackStack()
        Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun MyProfileCardLayout(
    context: Context,
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var email = rememberTextFieldState()
    var password = rememberTextFieldState()

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

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.dummy_user),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(120.dp)
                        .border(2.dp, OrangeBase, RoundedCornerShape(6.dp))
                )
                IconButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-70).dp, y = (14).dp)
                        .background(Cream) // small adjust so it hugs the corner
                ) {
                    Icon(
                        Icons.Default.AddPhotoAlternate,
                        contentDescription = "Camera",
                        modifier = Modifier.size(36.dp),
                        tint = OrangeBase
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Full Name",
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
                text = "Date of Birth",
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
                text = "Full Name",
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
                text = "Date of Birth",
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


            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(220.dp),
                    onClick = {

                    }, colors = ButtonColors(
                        containerColor = OrangeBase, contentColor = Cream,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        "Update",
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}


@Preview(apiLevel = 34, showSystemUi = true)
@Composable
private fun UI() {
//    MyProfileScreen()
}