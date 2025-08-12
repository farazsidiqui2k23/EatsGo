package com.example.eatsgo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eatsgo.Firebase.AuthState
import com.example.eatsgo.Firebase.AuthViewModel
import com.example.eatsgo.ui.theme.EatsGoTheme
import com.example.eatsgo.ui_screens.BoardingScreen
import com.example.eatsgo.ui_screens.HomeScreen
import com.example.eatsgo.ui_screens.Welcome_scr
import com.example.eatsgo.ui_screens.auth_screens.LogInScreen
import com.example.eatsgo.ui_screens.auth_screens.SignUpScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EatsGoTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EatsGo(modifier = Modifier.padding(innerPadding), this)
                }


//                val navController = rememberNavController()

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Welcome_scr(modifier = Modifier.padding(innerPadding))
////                    BoardingScr(modifier = Modifier.padding(innerPadding))
////                    LoginUI(this, modifier = Modifier.padding(innerPadding))
//SignUpScreen(this, navController, modifier = Modifier.padding(innerPadding))
////                    ForgetPassword_Scr(modifier = Modifier.padding(innerPadding))
////                    Fingerprint_Scr(modifier = Modifier.padding(innerPadding))
//                }


            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun EatsGo(modifier: Modifier = Modifier, context: Context) {

    val navController = rememberNavController()

    val authViewModel = viewModel<AuthViewModel>()
    val userState = authViewModel.authState.observeAsState()

    var alreadySigned by rememberSaveable { mutableStateOf(false) }

    when(userState.value){
        is AuthState.Authenticated -> {
            alreadySigned = true
        }
        AuthState.Unauthenticated -> { alreadySigned = false}
        is AuthState.onFailure -> {
            println((userState.value as AuthState.onFailure).message)
        }
        AuthState.onLoading -> {
            println("Loading")
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){ CircularProgressIndicator() }
        }
        null -> {}
    }

    NavHost(navController = navController,
        startDestination = if(alreadySigned)"HomeScreen" else { "BoardingScreen" }) {
        composable("BoardingScreen") {
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
            HomeScreen(modifier)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatsGoTheme {

    }
}