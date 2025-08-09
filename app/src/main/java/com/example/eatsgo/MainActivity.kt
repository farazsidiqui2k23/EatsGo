package com.example.eatsgo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eatsgo.ui.theme.EatsGoTheme
import com.example.eatsgo.ui_screens.BoardingScr
import com.example.eatsgo.ui_screens.Welcome_scr
import com.example.eatsgo.ui_screens.auth_screens.Fingerprint_Scr
import com.example.eatsgo.ui_screens.auth_screens.ForgetPassword_Scr
import com.example.eatsgo.ui_screens.auth_screens.Login_Scr
import com.example.eatsgo.ui_screens.auth_screens.SignUp_Scr

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EatsGoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Welcome_scr(modifier = Modifier.padding(innerPadding))
//                    BoardingScr(modifier = Modifier.padding(innerPadding))
//                    Login_Scr(modifier = Modifier.padding(innerPadding))
//                    SignUp_Scr(modifier = Modifier.padding(innerPadding))
//                    ForgetPassword_Scr(modifier = Modifier.padding(innerPadding))
//                    Fingerprint_Scr(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier, context: Context) {



    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "WelcomeScreen"){
        composable("WelcomeScreen"){

        }
        composable("WelcomeScreen"){}
        composable("WelcomeScreen"){}
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
        Greeting("Android")
    }
}