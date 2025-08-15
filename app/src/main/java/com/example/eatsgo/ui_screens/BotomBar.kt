package com.example.eatsgo.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase

@Composable
fun BotomBar(modifier: Modifier = Modifier, BydefaultLoc: Int) {
    val items = (0..3).toList()
    val bar_icons = mutableListOf(
        R.drawable.home_icon,
        R.drawable.dish_icon,
        R.drawable.fav_icon,
        R.drawable.suppport_icon
    )

    var selectedItem by rememberSaveable { mutableStateOf(BydefaultLoc) }

    Box(contentAlignment = Alignment.BottomCenter, modifier = modifier.fillMaxHeight()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = CardDefaults.cardColors(OrangeBase),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(items) { index ->
                    IconButton(
                        onClick = {
                            selectedItem = index
                        },
                        modifier = Modifier
                            .background(
                                if (selectedItem == index) Cream else Color.Transparent,
                                RoundedCornerShape(20.dp)
                            )
                            .size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(bar_icons[index]),
                            contentDescription = "",
                            tint = if (selectedItem == index) OrangeBase else Cream,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(apiLevel = 35, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
private fun UI() {
    HomeScreen()
}