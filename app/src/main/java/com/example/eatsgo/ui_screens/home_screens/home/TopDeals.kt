package com.example.eatsgo.ui_screens.home_screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.Restaurant
import com.example.eatsgo.api_setup.data_class.TopDeal
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import kotlinx.coroutines.delay

@Composable
fun TopDeals(
    modifier: Modifier = Modifier,
    navController: NavController,
    topDealRestaurant: List<Restaurant>,
    onTopDealClicked: (topDeal: TopDeal) -> Unit
) {


    val listState = rememberLazyListState()
    var currentIndex by rememberSaveable { mutableStateOf(0) }


    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentIndex = index
            }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrolling ->
                if (!isScrolling) {
                    // Jab user scroll chhod de, auto scroll resume ho
                    while (!listState.isScrollInProgress) {
                        delay(2000)
                        val nextIndex =
                            (listState.firstVisibleItemIndex + 1) % topDealRestaurant.size
                        listState.animateScrollToItem(nextIndex)
                    }
                }
            }
    }


    Column {
        Text(
            text = "Top Notch Deals",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = Brown
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(state = listState, flingBehavior = rememberSnapFlingBehavior(listState)) {
            items(topDealRestaurant) { restaurant ->

                Card(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(320.dp)
                        .height(130.dp)
                        .clickable {
                            onTopDealClicked(restaurant.top_deal)
                            navController.navigate("TopDealScreen")
                        },
                    colors = CardDefaults.cardColors(OrangeBase)

                ) {
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            //ofer desc
                            Icon(
                                painter = painterResource(R.drawable.hole_circle),
                                contentDescription = "",
                                tint = YellowBase,
                                modifier = Modifier
                                    .size(50.dp)
                                    .offset(x = 120.dp, y = -30.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.hole_circle),
                                contentDescription = "",
                                tint = YellowBase,
                                modifier = Modifier
                                    .size(50.dp)
                                    .offset(x = -20.dp, y = 100.dp)
                            )
                            Column(
                                modifier
                                    .fillMaxSize()
                                    .padding(12.dp, 0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = restaurant.top_deal.name,
                                    maxLines = 1,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Cream
                                )
                                Text(
                                    text = restaurant.top_deal.tagline,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    color = Cream,
                                    modifier = Modifier.offset(x = 0.dp, y = -8.dp)
                                )
                                Text(
                                    text = "${restaurant.top_deal.discount} OFF",
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontSize = 28.sp,
                                    color = Cream
                                )


                            }
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            // image
                            Image(
                                painter = painterResource(id = R.drawable.dishoffer),
                                contentDescription = "Deals Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            LazyRow(modifier = Modifier.padding(0.dp, 14.dp)) {
                items(topDealRestaurant.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(8.dp)
                            .width(24.dp)
                            .clip(CircleShape)
                            .background(if (index == currentIndex) OrangeBase else Yellow2)

                    )
                }
            }
        }
    }
}