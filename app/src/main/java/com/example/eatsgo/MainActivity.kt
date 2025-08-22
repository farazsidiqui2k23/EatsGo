@file:OptIn(
    ExperimentalAnimationApi::class, ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class, ExperimentalAnimationApi::class
)

package com.example.eatsgo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.eatsgo.Firebase.AuthState
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.api_setup.data_class.Deal
import com.example.eatsgo.api_setup.data_class.Item
import com.example.eatsgo.api_setup.data_class.Restaurant
import com.example.eatsgo.api_setup.data_class.TopDeal
import com.example.eatsgo.ui.theme.EatsGoTheme
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.YellowBase
import com.example.eatsgo.ui_screens.BoardingScreen
import com.example.eatsgo.ui_screens.DealScreen
import com.example.eatsgo.ui_screens.FoodItemScreen
import com.example.eatsgo.ui_screens.RestaurantScreen
import com.example.eatsgo.ui_screens.Welcome_scr
import com.example.eatsgo.ui_screens.auth_screens.LogInScreen
import com.example.eatsgo.ui_screens.auth_screens.SignUpScreen
import com.example.eatsgo.ui_screens.home_screens.HomeScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.ContactScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.HelpScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.MyProfileScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.OrderScreen
import com.example.eatsgo.ui_screens.profile_drawer_screens.SettingScreen
import com.example.yourapp.ui.screens.TopDealScreen

import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EatsGoTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EatsGo(modifier = Modifier.padding(innerPadding), this)
                }


                val navController = rememberNavController()
                val authViewModel = viewModel<AuthViewModel>()

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Welcome_scr(modifier = Modifier.padding(innerPadding))
////                    BoardingScr(modifier = Modifier.padding(innerPadding))
////                    LoginUI(this, modifier = Modifier.padding(innerPadding))
//SignUpScreen(this, navController, modifier = Modifier.padding(innerPadding))
////                    ForgetPassword_Scr(modifier = Modifier.padding(innerPadding))
////                    Fingerprint_Scr(modifier = Modifier.padding(innerPadding))
//                    AlertDialogBox(modifier = Modifier.padding(innerPadding), true)
//                    OrderScreen(modifier = Modifier.padding(innerPadding))
//                    FavouriteScreen(modifier = Modifier.padding(innerPadding))
//                    HelpScreen(modifier = Modifier.padding(innerPadding))
//                    ImageChecking(modifier = Modifier.padding(innerPadding))
//                    SettingScreen(modifier = Modifier.padding(innerPadding))
//                    MyProfileScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        context = this,
//                        authViewModel = authViewModel,
//                        navController = navController
//                    )
//                    HomeScreen(modifier = Modifier.padding(innerPadding))
//                    NavigationDrawerScreen(modifier = Modifier.padding(innerPadding))
//                }
//

            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun EatsGo(modifier: Modifier = Modifier, context: Context) {

    val systemUIcontroller = rememberSystemUiController()
    val statusBarColor = YellowBase

    SideEffect {
        systemUIcontroller.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = false
        )
    }

    val navController = rememberNavController()

    val authViewModel = viewModel<AuthViewModel>()
    val userState = authViewModel.authState.observeAsState()

    var alreadySigned by rememberSaveable { mutableStateOf(false) }

    when (val state = userState.value) {
        is AuthState.Authenticated -> {
            alreadySigned = true
        }

        AuthState.Unauthenticated -> {
            alreadySigned = false
        }

        is AuthState.onFailure -> {
            Toast.makeText(context, state.message.toString(), Toast.LENGTH_SHORT).show()
        }

        is AuthState.onLoading -> {
            Box(
                modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }

        null -> {}
    }

    var clickedRestaurant: Restaurant? by rememberSaveable { mutableStateOf(null) }
    var clickedPersonItem: Item? by rememberSaveable { mutableStateOf(null) }
    var clickedDealItem: Deal? by rememberSaveable { mutableStateOf(null) }
    var clickedTopDeal: TopDeal? by rememberSaveable { mutableStateOf(null) }

    println(clickedTopDeal)

    println("navhost recompose")

    AnimatedNavHost(
        navController = navController,
        startDestination = if (alreadySigned) "HomeScreen" else {
            "BoardingScreen"
        }
    ) {
        composable("BoardingScreen", enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }) {
            BoardingScreen(navController, modifier)
        }
        composable("WelcomeScreen") {
            Welcome_scr(navController, modifier)
        }
        composable("SignUpScreen") {
            SignUpScreen(authViewModel, context, navController, modifier)
        }
        composable("LogInScreen") {
            LogInScreen(context, authViewModel, navController, modifier)
        }
        composable("HomeScreen") {
            HomeScreen(
                modifier, navController,
                restaurantClick = { restaurantData -> clickedRestaurant = restaurantData },
                onTopDealClicked = { topdeal -> clickedTopDeal = topdeal },
            )
        }
        composable("orders_screen",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth }, // start off-screen to the right
                    animationSpec = tween(durationMillis = 300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth }, // slide back to right
                    animationSpec = tween(durationMillis = 300)
                )
            }) {
            OrderScreen(modifier, navController)
        }

        composable("profile_screen") {
            MyProfileScreen(
                modifier = modifier, navController = navController,
                context = context,
                authViewModel = authViewModel
            )
        }
        composable("contact_screen") {
            ContactScreen(modifier = modifier, navController = navController)
        }

        composable("help_screen") {
            HelpScreen(modifier = modifier, navController = navController)
        }

        composable("settings_screen") {
            SettingScreen(modifier = modifier, navController = navController)
        }
        composable("logout") {
            authViewModel.UserSignOut()
        }
        composable("RestaurantScreen") {
            clickedRestaurant?.let { it1 ->
                RestaurantScreen(
                    modifier, it1, navController,
                    onItemClicked = { item -> clickedPersonItem = item },
                    onDealClicked = { dealitem -> clickedDealItem = dealitem }
                )
            }
        }
        composable("FoodItemScreen") {
            clickedPersonItem?.let { it1 ->
                FoodItemScreen(
                    modifier,
                    foodItem = it1,
                    navController = navController,
                )
            }
        }

        composable("DealItemScreen") {
            clickedDealItem?.let { it1 -> DealScreen(modifier, it1, navController) }
        }

        composable("TopDealScreen") {
            clickedTopDeal?.let { it1 ->
                TopDealScreen(
                    modifier = modifier,
                    topDeal = it1,
                    navController = navController
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatsGoTheme {

    }
}