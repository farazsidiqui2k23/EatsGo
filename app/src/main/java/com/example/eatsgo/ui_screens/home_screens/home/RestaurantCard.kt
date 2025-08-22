package com.example.eatsgo.ui_screens.home_screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.Restaurant
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun RestaurantCard(
    modifier: Modifier = Modifier,
    restaurant: Restaurant,
    navController: NavController,
    onClick: (restaurant: Restaurant) -> Unit
) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable {
                    onClick(restaurant)
                    navController.navigate("RestaurantScreen")
                },
            colors = CardDefaults.cardColors(OrangeBase)
        ) {
            Image(
                painter = painterResource(R.drawable.dish),
                contentDescription = "Dummy Img",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = restaurant.name,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
            color = Brown
        )
        LazyRow() {
            items(restaurant.items) { item ->
                Text(
                    text = "- ${item.name}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = YellowBase
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Star, contentDescription = "Rating", tint = OrangeBase)
            Text(
                text = restaurant.rating.toString(),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = Brown
            )
            Icon(
                Icons.Default.DeliveryDining,
                contentDescription = "Delivery",
                tint = OrangeBase
            )
            Text(
                text = "Free",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = Brown
            )
            Icon(Icons.Default.AccessTimeFilled, contentDescription = "Time", tint = OrangeBase)
            Text(
                text = "10 min",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = Brown
            )
        }
    }
}