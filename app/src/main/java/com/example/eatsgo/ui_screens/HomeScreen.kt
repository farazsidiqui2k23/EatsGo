@file:OptIn(ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

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
                    .padding(12.dp, 8.dp)
                    .border(1.dp, Color.Green),
                contentAlignment = Alignment.CenterStart
            ) {
               TopBar(modifier)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.Red)
            ) {

            }
        }
    }
}


@Composable
fun TopBar(modifier: Modifier = Modifier) {

    val search = rememberTextFieldState()

    val focusRequester = remember { FocusRequester() }

    val isKeyboardVisible = WindowInsets.isImeVisible

    val input_modifier = Modifier
        .fillMaxWidth(.6f)
        .padding(horizontal = 2.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Cream)
        .padding(12.dp)
        .focusRequester(focusRequester)
        .clickable {
            if (!isKeyboardVisible) {
                focusRequester.requestFocus()
            }
        }
    Column {
        Row {
                BasicTextField(
                    state = search,
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
                                onClick = {
                                    //search
                                },
                                modifier = Modifier
                                    .then(Modifier.size(24.dp))
                            ) {
                                Icon(
                                   Icons.Default.Search,
                                    contentDescription = "", tint = OrangeBase
                                )
                            }

                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

            Spacer(modifier = Modifier.width(20.dp))
            Row(modifier = Modifier.border(1.dp, Color.Green)) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                Icon(Icons.Default.Notifications, contentDescription = "Notification")
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
        }
        Text(text = "Good Morning")
        Text(text = "Risi And Shine! It's Breakfast Time")

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UI() {
    HomeScreen()
}