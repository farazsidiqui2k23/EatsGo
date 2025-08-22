package com.example.eatsgo.ui_screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.Deal
import com.example.eatsgo.api_setup.data_class.Item
import com.example.eatsgo.api_setup.data_class.Restaurant
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase


@Composable
fun RestaurantScreen(
    modifier: Modifier = Modifier,
    restaurant: Restaurant,
    navController: NavController,
    onItemClicked: (item: Item) -> Unit,
    onDealClicked: (dealItem: Deal) -> Unit,


    ) {
    MainUILayout(
        modifier = modifier,
        title = restaurant.name,
        changeTitle = restaurant.name,
        content = {
            RestaurantCardLayout(
                restaurant, modifier, navController,
                onItemClicked = { item -> onItemClicked(item) },
                onDealClicked = { dealitem -> onDealClicked(dealitem) }
            )
        },
    ) {
        navController.popBackStack()
    }

}

@Composable
fun RestaurantCardLayout(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    navController: NavController,
    onItemClicked: (item: Item) -> Unit,
    onDealClicked: (dealItem: Deal) -> Unit,
) {

    val restaurantItem = restaurant.items
    val dealItem = restaurant.deals

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Cream)
    ) {
        Column {
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Let's Discover all Dishes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangeBase,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
                )

            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(12.dp, 0.dp)
                    .fillMaxHeight()
            ) {
                item {
                    Text(
                        text = "Family Combo",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Brown,
                        modifier = Modifier.padding(12.dp, 0.dp)
                    )
                }

                items(dealItem) { dealItem ->
                    DealCard(
                        modifier,
                        dealItem,
                        navController
                    ) { dealitem -> onDealClicked(dealitem) }
                }
                item {
                    Text(
                        text = "1 Person Items",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Brown,
                        modifier = Modifier.padding(12.dp, 0.dp)
                    )
                }

                items(restaurantItem.chunked(2)) { rowItems ->   // 2 cards in each row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { item ->
                            ItemCard(
                                item = item,
                                modifier = Modifier.weight(1f), navController = navController
                            ) { item ->
                                onItemClicked(item)
                            }
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
fun ItemCard(
    modifier: Modifier = Modifier,
    item: Item,
    navController: NavController,
    onItemClicked: (item: Item) -> Unit
) {

    Card(
        modifier = modifier.clickable {
            onItemClicked(item)
            navController.navigate("FoodItemScreen")
        },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            // Image (using drawable as placeholder)
            Image(
                painter = painterResource(id = R.drawable.broast),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )

        }

        // Texts
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OrangeBase,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.desc,
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

@Composable
fun DealCard(
    modifier: Modifier = Modifier,
    item: Deal,
    navController: NavController,
    onDealClicked: (dealItem: Deal) -> Unit
) {
    Card(
        modifier = Modifier.clickable {
            onDealClicked(item)
            navController.navigate("DealItemScreen")
        },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            // Image (using drawable as placeholder)
            Image(
                painter = painterResource(id = R.drawable.broast),
                contentDescription = item.deal_name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )

        }

        // Texts
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = item.deal_name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OrangeBase,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            LazyRow {
                items(item.deal_items) { dealItem ->
                    Text(
                        text = " - ${dealItem.name}",
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
    }
}




