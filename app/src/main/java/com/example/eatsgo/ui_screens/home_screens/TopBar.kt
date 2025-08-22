package com.example.eatsgo.ui_screens.home_screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui_screens.getGreetingAndTagline


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopBar(modifier: Modifier = Modifier, navDrawer: (itemIndex: Int, itemState: Boolean) -> Unit) {

    val (headline, tagline) = getGreetingAndTagline()

    val search = rememberTextFieldState()

    val focusRequester = remember { FocusRequester() }

    val isKeyboardVisible = WindowInsets.isImeVisible

    val targetWidth = if (isKeyboardVisible) 1f else .58f
    val animatedTextField by animateFloatAsState(targetValue = targetWidth)

    val inputmodifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 2.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Cream)
        .padding(8.dp)
        .focusRequester(focusRequester)
        .clickable {
            if (!isKeyboardVisible) {
                focusRequester.requestFocus()
            }
        }

    val icons =
        mutableListOf(Icons.Default.ShoppingCart, Icons.Default.Notifications, Icons.Default.Person)
    val icon_index = (0..2).toList()
    Column {
        Row {
            Box(modifier = Modifier.fillMaxWidth(animatedTextField)) {
                BasicTextField(
                    state = search,
                    inputmodifier,
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
            }


            Spacer(modifier = Modifier.width(14.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                items(icon_index) { index ->

                    IconButton(
                        onClick = {
                            navDrawer(index, true)
                        },
                        modifier = Modifier
                            .background(Cream, RoundedCornerShape(16.dp))
                            .size(38.dp)
                    ) {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = "",
                            tint = OrangeBase,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }


            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = headline,
            fontSize = 28.sp,
            color = Cream,
            fontFamily = FontFamily(Font(R.font.poppins_bold))
        )
        Text(
            text = tagline,
            fontSize = 16.sp,
            color = OrangeBase,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

    }
}