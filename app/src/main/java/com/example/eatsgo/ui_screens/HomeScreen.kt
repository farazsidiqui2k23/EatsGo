@file:OptIn(ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import com.google.firebase.components.Lazy

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f)
                    .padding(12.dp, 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                TopBar(modifier)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HomeCardLayout()
            }
        }
    }
}


@Composable
fun TopBar(modifier: Modifier = Modifier) {

    val search = rememberTextFieldState()

    val focusRequester = remember { FocusRequester() }

    val isKeyboardVisible = WindowInsets.isImeVisible

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
            Box(modifier = Modifier.fillMaxWidth(.58f)) {
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
                        onClick = {},
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
            text = "Good Morning",
            fontSize = 28.sp,
            color = Cream,
            fontFamily = FontFamily(Font(R.font.poppins_bold))
        )
        Text(
            text = "Risi And Shine! It's Breakfast Time",
            fontSize = 16.sp,
            color = OrangeBase,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

    }
}

@Composable
fun HomeCardLayout(modifier: Modifier = Modifier) {
    Card(
        modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            MenuCard(modifier)
        }
    }
}

@Composable
fun MenuCard(modifier: Modifier = Modifier) {

    val FoodIcons = mutableListOf(
        R.drawable.burger_icon,
        R.drawable.meal_icon,
        R.drawable.pizza_icon,
        R.drawable.desert_icon,
        R.drawable.drink_icon
    )

    val list = (0..4).toList()

    LazyRow(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        items(list) { index ->
            IconButton(onClick = {},
                modifier = Modifier
                    .size(56.dp, 70.dp)
                    .background(Yellow2, RoundedCornerShape(20.dp))
            ) {
                Icon(
                    painter = painterResource(id = FoodIcons[index]),
                    contentDescription = "",
                    tint = OrangeBase,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

    Spacer(modifier= Modifier.fillMaxWidth().height(1.dp).background(OrangeBase).padding(0.dp,20.dp))

}

@Preview(apiLevel = 34, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
private fun UI() {
    HomeScreen()
}