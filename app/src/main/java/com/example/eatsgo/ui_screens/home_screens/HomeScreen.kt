@file:OptIn(ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens.home_screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
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
import com.example.eatsgo.ui_screens.home_screens.home.MenuCard
import com.example.eatsgo.ui_screens.home_screens.home.RestaurantCard
import com.example.eatsgo.ui_screens.home_screens.home.TopDeals
import com.example.eatsgo.ui_screens.profile_drawer_screens.ContactScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.FavouriteScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.OrderScreen
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, navController: NavController,
    restaurantClick: (restaurant: Restaurant) -> Unit, onTopDealClicked: (topDeal: TopDeal) -> Unit
) {

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
                    MainHomePage(
                        modifier, it,
                        restaurantClick = { Click -> restaurantClick(Click) },
                        navController = navController,
                        onTopDealClicked = { deal -> onTopDealClicked(deal) }
                    ) { index, state ->
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
                FavouriteScreen(modifier, navController)
            }

            3 -> {
                ContactScreen(modifier, navController)
            }
        }

        BotomBar(modifier) { i ->
            bottomBarItem = i
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
    navController: NavController,
    restaurantClick: (restaurant: Restaurant) -> Unit,
    onTopDealClicked: (topDeal: TopDeal) -> Unit,
    NavigationDrawer: (drawerIndex: Int, drawerState: Boolean) -> Unit,

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
                HomeCardLayout(
                    modifier, eatsGoAppData, navController,
                    restaurantClick = { click -> restaurantClick(click) },
                    onTopDealClicked = { deal -> onTopDealClicked(deal) }
                ) { height ->
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
    navController: NavController,
    restaurantClick: (restaurant: Restaurant) -> Unit,
    onTopDealClicked: (topDeal: TopDeal) -> Unit,
    scrollProgress: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    val scrollPx = listState.firstVisibleItemScrollOffset
    val progress = scrollPx / 100
    scrollProgress(progress)

    var seletedMenu by rememberSaveable { mutableStateOf("meal") }

    var restaurant: List<Restaurant> =
        if (seletedMenu == "meal") appdata.restaurants else appdata.restaurants.filter { it.famous_for == seletedMenu }
    Log.d("FarazSidd", restaurant.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column() {
            MenuCard(modifier, seletedMenu) { menu ->
                seletedMenu = menu
            }
            LazyColumn(
                modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 64.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                val topDealRestaurant = restaurant.filter { it.has_top_deal }.take(5)
                item() {
                    AnimatedVisibility(
                        visible = if (topDealRestaurant.isNotEmpty()) true else false,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Divider(color = Orange2)

                        TopDeals(
                            modifier, navController, topDealRestaurant,
                            onTopDealClicked = { deal -> onTopDealClicked(deal) }
                        )
                    }

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

                items(restaurant) { restaurant ->
                    RestaurantCard(
                        modifier = modifier,
                        restaurant = restaurant,
                        navController = navController
                    ) { click ->
                        restaurantClick(click)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }
}


