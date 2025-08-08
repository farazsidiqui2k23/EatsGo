package com.example.eatsgo

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
import com.example.eatsgo.ui.theme.EatsGoTheme
import com.example.eatsgo.ui_screens.BoardingScr
import com.example.eatsgo.ui_screens.Welcome_scr
import com.example.eatsgo.ui_screens.auth_screens.Login_Scr

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EatsGoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Welcome_scr(modifier = Modifier.padding(innerPadding))
//                    BoardingScr(modifier = Modifier.padding(innerPadding))
                    Login_Scr(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatsGoTheme {
        Greeting("Android")
    }
}