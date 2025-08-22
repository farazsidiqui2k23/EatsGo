package com.example.eatsgo.ui_screens.home_screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    defaultItem: String,
    onClick: (selectedMenu: String) -> Unit
) {

    val FoodIcons = listOf(
        Pair("meal", R.drawable.meal_icon),
        Pair("burger", R.drawable.burger_icon),
        Pair("pizza", R.drawable.pizza_icon),
        Pair("dessert", R.drawable.desert_icon),
        Pair("drink", R.drawable.drink_icon)
    )

    var selectedItem by rememberSaveable { mutableStateOf(defaultItem) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 12.dp, 12.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(FoodIcons) { item ->

            IconButton(
                onClick = {
                    onClick(item.first)
                    selectedItem = item.first
                },
                modifier = Modifier
                    .size(56.dp, 70.dp)
                    .background(
                        if (selectedItem == item.first) YellowBase else Yellow2,
                        RoundedCornerShape(20.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(id = item.second),
                    contentDescription = "",
                    tint = OrangeBase,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}