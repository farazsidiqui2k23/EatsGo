@file:OptIn(ExperimentalLayoutApi::class)

package com.example.eatsgo.ui_screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.Firebase.RealtimeDB
import com.example.eatsgo.Firebase.userData
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import com.example.eatsgo.ui_screens.drawer_screens.NavigationDrawerScreen
import com.example.eatsgo.ui_screens.drawer_screens.noRippleClickable


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {

    var whichDrawer by rememberSaveable { mutableIntStateOf(0) }
    var drawerState by rememberSaveable { mutableStateOf(false) }

    var progress by remember { mutableFloatStateOf(0f) }

    val currentFraction by animateFloatAsState(0.2f - (progress * (0.2f - 0.1f)))


    val authViewModel = viewModel<AuthViewModel>()

    var userProfileInfo: userData? by remember { mutableStateOf(null) }

    val RealtimeDB = authViewModel.realtimeDB.observeAsState()
    val userId = authViewModel.auth.currentUser!!.uid

    LaunchedEffect(Unit){ authViewModel.getProfileData(userId) }


    LaunchedEffect(RealtimeDB.value){
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



    Box() {

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
                        .fillMaxHeight(currentFraction)
                        .padding(12.dp, 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    TopBar(modifier) { index, state ->
                        whichDrawer = index
                        drawerState = state
                    }
                }
                //card box
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HomeCardLayout(){ height ->
                        progress = height.toFloat()
                    }
                }
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
        BotomBar(modifier, 0)
        userProfileInfo?.let {
            NavigationDrawerScreen(modifier, navController, drawerState, whichDrawer, it) { state ->
                drawerState = state
            }
        }
    }
}


@Composable
fun TopBar(modifier: Modifier = Modifier, navDrawer: (itemIndex: Int, itemState: Boolean) -> Unit) {

    val search = rememberTextFieldState()

    val focusRequester = remember { FocusRequester() }

    val isKeyboardVisible = WindowInsets.isImeVisible

    val targetWidth = if (isKeyboardVisible) 1f else .58f
    val animatedTextField by animateFloatAsState(targetValue = targetWidth)

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
            Box(modifier = Modifier.fillMaxWidth(animatedTextField)) {
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
                        onClick = {
                            navDrawer(index, true)
                        },
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
fun HomeCardLayout(modifier: Modifier = Modifier, scrollProgress : (Int) -> Unit) {
    val listState = rememberLazyListState()

    val scrollPx = listState.firstVisibleItemScrollOffset
    val progress = scrollPx / 100
scrollProgress(progress)


    Card(
        modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(Cream),
        shape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column() {
            MenuCard(modifier)
            LazyColumn(
                modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 64.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {

                    Divider(color = Orange2.copy(alpha = 0.4f))
                    TopDeals(modifier)

                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Orange2)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Restaurants(modifier)
                    Restaurants(modifier)
                    Restaurants(modifier)
                    Restaurants(modifier)
                    Restaurants(modifier)


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

    LazyRow(modifier.fillMaxWidth().padding(12.dp,12.dp,12.dp,0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
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
fun TopDeals(modifier: Modifier = Modifier) {
    val displayedDeal = (0..4).toList()
    Column {
        Text(
            text = "Top Notch Deals",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = Brown
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp), colors = CardDefaults.cardColors(
                OrangeBase
            )
        ) {

            Row {
                Box(modifier = Modifier.fillMaxWidth(.5f)) {
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
                            text = "30% OFF",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 28.sp,
                            color = Cream
                        )


                    }
                }
                Box(modifier = Modifier.fillMaxWidth()) {
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
        Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            LazyRow(modifier = Modifier.padding(0.dp, 14.dp)) {
                items(displayedDeal) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(8.dp)
                            .width(24.dp)
                            .clip(CircleShape)
                            .background(Yellow2)

                    )
                }
            }
        }
    }
}

@Composable
fun Restaurants(modifier: Modifier = Modifier) {

    Column {
        Text(
            text = "Explore Restaurants",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = Brown
        )
        Spacer(modifier = Modifier.height(8.dp))

        //lazy column for restaurants

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
                text = "Ijaz Pakwan",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                color = Brown
            )
            Text(
                text = "- Rice - Biryani - Pizza - Roll",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = YellowBase
            )
            Row(
                modifier.fillMaxWidth(.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(Icons.Default.Star, contentDescription = "Rating", tint = OrangeBase)
                Text(
                    text = "4.5",
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
}

