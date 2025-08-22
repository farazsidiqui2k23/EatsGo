package com.example.eatsgo.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.Deal
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun DealScreen(
    modifier: Modifier = Modifier,
    deal: Deal,
    navController: NavController
) {
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    var pagerIndex by rememberSaveable { mutableIntStateOf(0) }

    // 🧮 Calculate totals
    val totalAmount = deal.deal_items.sumOf { it.price }
    val discountedAmount = totalAmount - (totalAmount * 0.05)

    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase) // replace with your own color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(YellowBase, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            // 🔙 Back + Heading + Fav
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp, 12.dp, 32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp),
                        tint = OrangeBase // replace with your own color
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = deal.deal_name,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 20.sp,
                        color = OrangeBase
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton(onClick = { isFavourite = !isFavourite }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favourite",
                            modifier = Modifier.size(24.dp),
                            tint = if (isFavourite) OrangeBase else Orange2 // replace Orange2
                        )
                    }
                }
            }

            // 🖼️ Deal Images LazyRow
            Card(
                colors = CardDefaults.cardColors(Cream), // replace with your color
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedCornerShape(24.dp, 24.dp)
            ) {
                Column(modifier.padding(16.dp)) {
                    val listState = rememberLazyListState()
                    val coroutineScope = rememberCoroutineScope()

                    // observe index change
                    LaunchedEffect(listState) {
                        snapshotFlow { listState.firstVisibleItemScrollOffset to listState.firstVisibleItemIndex }
                            .collect { (offset, index) ->
                                pagerIndex = if (offset > 100) index + 1 else index
                            }
                    }

                    LazyRow(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        itemsIndexed(deal.deal_items) { _, item ->
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .fillParentMaxHeight()
                                    .width(280.dp)
                                    .padding(end = 12.dp),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                AsyncImage(
                                    model = item.pic,
                                    contentDescription = item.name,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    // ⬤ Dots Indicator
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(deal.deal_items.size) { index ->
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(8.dp)
                                    .background(
                                        if (index == pagerIndex) OrangeBase else Orange2,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Orange2)

                    // 🍴 Deal Items (Name + Price)
                    deal.deal_items.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.name,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "${item.price} Rs.",
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontSize = 16.sp,
                                color = OrangeBase
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 📖 Deal Description
                    Text(
                        text = "This deal includes ${deal.deal_items.size} items with fresh taste and delicious flavor.",
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // 💰 Total & Discount
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Total Amount",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Brown
                        )
                        Text(
                            "$totalAmount Rs.",
                            color = Brown,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Discounted (5%)",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Brown
                        )
                        Text(
                            "${"%.2f".format(discountedAmount)} Rs.",
                            color = OrangeBase,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // 🛒 Add to Cart button
                    Button(
                        onClick = { /* TODO: add deal to cart */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeBase),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Add to Cart",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

