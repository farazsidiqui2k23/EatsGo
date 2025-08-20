@file:OptIn(ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens.home_screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.Firebase.RealtimeDB
import com.example.eatsgo.Firebase.userData
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.ResataurantViewModel
import com.example.eatsgo.api_setup.RestaurantDataState
import com.example.eatsgo.api_setup.data_class.EatsGoAppData
import com.example.eatsgo.api_setup.data_class.Restaurant
import com.example.eatsgo.api_setup.data_class.TopDeal
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import com.example.eatsgo.ui_screens.drawer_screens.NavigationDrawerScreen
import com.example.eatsgo.ui_screens.drawer_screens.noRippleClickable
import com.example.eatsgo.ui_screens.profile_drawer_screens.ContactScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.OrderScreen


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {


    var whichDrawer by rememberSaveable { mutableIntStateOf(0) }
    var drawerState by rememberSaveable { mutableStateOf(false) }


    var bottomBarItem by rememberSaveable { mutableStateOf(0) }

    val authViewModel = viewModel<AuthViewModel>()

    var userProfileInfo: userData? by remember { mutableStateOf(null) }

    val RealtimeDB = authViewModel.realtimeDB.observeAsState()
    val userId = authViewModel.auth.currentUser!!.uid

    LaunchedEffect(Unit) { authViewModel.getProfileData(userId) }


    LaunchedEffect(RealtimeDB.value) {
        when (val data = RealtimeDB.value) {
            is RealtimeDB.onFailure -> {
//                Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
            }

            is RealtimeDB.onSuccess -> {

                userProfileInfo = data.userData
            }

            is RealtimeDB.onLoading -> {
//                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }

            null -> {
                println("Still null")
            }
        }
    }

    val restaurantViewModel = viewModel<ResataurantViewModel>()
    val restaurantDataState = restaurantViewModel.restaurantState.collectAsState()

    var eatsGoAppData: EatsGoAppData? by remember { mutableStateOf(null) }

    LaunchedEffect(restaurantDataState.value) {
        when (val data = restaurantDataState.value) {
            is RestaurantDataState.onSuccess -> {
                eatsGoAppData = data.data.record
                println(eatsGoAppData)
            }

            is RestaurantDataState.onFailure -> {
                println(data.message)
            }

            is RestaurantDataState.onLoading -> {
                //shimmer effect
                println("Loading")
            }
        }
    }


    Box() {

        when (bottomBarItem) {
            0 -> {
                eatsGoAppData?.let {
                    MainHomePage(modifier, it) { index, state ->
                        whichDrawer = index
                        drawerState = state
                    }
                }
                if (drawerState) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    color = Orange2,
                                    blendMode = BlendMode.Multiply
                                )
                            }
                            .noRippleClickable { drawerState = false } // tap outside closes
                    )
                }
            }

            1 -> {
                OrderScreen(modifier, navController)
            }

            2 -> {
                //Favourite
            }

            3 -> {
                ContactScreen(modifier, navController)
            }
        }

        BotomBar(modifier) { i ->
            bottomBarItem = i
            println(i)
        }


        userProfileInfo?.let {
            NavigationDrawerScreen(modifier, navController, drawerState, whichDrawer, it) { state ->
                drawerState = state
            }
        }
    }
}


@Composable
fun MainHomePage(
    modifier: Modifier = Modifier,
    eatsGoAppData: EatsGoAppData,
    NavigationDrawer: (drawerIndex: Int, drawerState: Boolean) -> Unit
) {


    var progress by remember { mutableFloatStateOf(0f) }

    val currentFraction by animateFloatAsState(0.2f - (progress * (0.2f - 0.1f)))

    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            //top Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f)
                    .padding(12.dp, 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                TopBar(modifier) { index, state ->

                    NavigationDrawer(index, state)
                }
            }
            //card box
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HomeCardLayout(modifier, eatsGoAppData) { height ->
                    progress = height.toFloat()
                }
            }
        }
    }
}


@Composable
fun HomeCardLayout(
    modifier: Modifier = Modifier,
    appdata: EatsGoAppData,
    scrollProgress: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    val scrollPx = listState.firstVisibleItemScrollOffset
    val progress = scrollPx / 100
    scrollProgress(progress)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column() {
            MenuCard(modifier)
            LazyColumn(
                modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 64.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Divider(color = Orange2)
                }
                item() {
                    TopDeals(modifier, appdata.restaurants)
                }
                item {
                    Divider(color = Orange2)
                }
                item {
                    Text(
                        text = "Explore Restaurants",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Brown
                    )
                }

                items(appdata.restaurants) { restaurant ->
                    Restaurants(
                        modifier = modifier,
                        restaurant = restaurant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
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

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 12.dp, 12.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(list) { index ->
            IconButton(
                onClick = {},
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
}

@Composable
fun TopDeals(modifier: Modifier = Modifier, restaurant: List<Restaurant>) {
    val displayedDeal = restaurant.filter { it.has_top_deal }
        .take(5)
    val listState = rememberLazyListState()
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentIndex = index
                println("Now on index $index")
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
            items(displayedDeal) { restaurant ->

        Card(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(320.dp)
                .height(130.dp)
                .clickable { },
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
                        modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Experience our",
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp,
                            color = Cream
                        )
                        Text(
                            text = "delicious new deal",
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp,
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
                items(displayedDeal.size) { index ->
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

@Composable
fun Restaurants(modifier: Modifier = Modifier, restaurant: Restaurant) {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), colors = CardDefaults.cardColors(
                OrangeBase
            )
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

