package com.example.eatsgo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eatsgo.ui.theme.EatsGoTheme
import com.example.eatsgo.ui_screens.Welcome_scr
import com.example.eatsgo.ui_screens.auth_screens.LoginUI
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

@Composable
fun EatsGo(modifier: Modifier = Modifier, context: Context) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "WelcomeScreen"){
        composable("WelcomeScreen"){
Welcome_scr(navController, modifier)
        }
        composable("SignUpScreen"){

        }
        composable("LogInScreen"){

        }
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatsGoTheme {
        EatsGo(
            context = LocalContext.current
        )
    }
}