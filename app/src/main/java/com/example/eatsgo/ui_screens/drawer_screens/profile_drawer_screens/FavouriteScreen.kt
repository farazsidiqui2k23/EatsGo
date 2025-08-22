package com.example.eatsgo.ui_screens.drawer_screens.profile_drawer_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui_screens.MainUILayout

// 🔹 Dummy Data
val dummyFoodList = listOf(
    FoodItem("1", "Chicken Curry", "Delicious spicy chicken curry", "", false),
    FoodItem("2", "Chicken Burger", "Crispy chicken burger with cheese", "", true),
    FoodItem("3", "Broccoli Lasagna", "Healthy green lasagna with broccoli", "", false),
    FoodItem("4", "Mexican Appetizer", "Nachos with salsa & guacamole", "", false),
    FoodItem("5", "Spicy Wings", "Hot & crispy wings", "", true),
    FoodItem("6", "Milkshake", "Cold chocolate & strawberry shakes", "", false)
)

@Composable
fun FavouriteScreen(modifier: Modifier = Modifier, navController: NavController) {
    MainUILayout(
        modifier = modifier,
        title = "Favourite",
        changeTitle = "Favourite",
        content = { FavouriteCardLayout(items = dummyFoodList) },
    ) {
        println("Back Pressed")
        navController.popBackStack()
    }
}

@Composable
fun FavouriteCardLayout(
    items: List<FoodItem>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Cream)
    ) {
        Column {
            Text(
                text = "It's Time to buy your Fav dish",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeBase,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                items(items.chunked(2)) { rowItems ->   // 2 cards in each row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { item ->
                            FoodCard(
                                item = item,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FoodCard(
    item: FoodItem,
    modifier: Modifier = Modifier,
    onFavoriteChange: (Boolean) -> Unit = {}
) {
    var fav by remember(item.id) { mutableStateOf(item.isFavorite) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            // Image (using drawable as placeholder)
            Image(
                painter = painterResource(id = R.drawable.broast),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )

            // Favorite button (top-right)
            IconButton(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.TopEnd)
                    .then(Modifier.size(24.dp)),

                onClick = {
                    fav = !fav
                    onFavoriteChange(fav)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.fav_icon),
                    contentDescription = "favorite",
                    tint = if (fav) {
                        OrangeBase
                    } else Orange2,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp)
                )
            }
        }

        // Texts
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OrangeBase,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.subtitle,
                fontSize = 13.sp,
                color = Brown,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )
        }
    }
}

// 🔹 Data class
data class FoodItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)


