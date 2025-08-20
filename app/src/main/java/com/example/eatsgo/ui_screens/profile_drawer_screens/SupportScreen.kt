package com.example.eatsgo.ui_screens.profile_drawer_screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eatsgo.ui_screens.MainUILayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase


@Composable
fun HelpScreen(modifier: Modifier = Modifier, navController: NavController) {
    MainUILayout(
        modifier = modifier,
        title = "Help",
        changeTitle = "Help",
        content = { SupportCardLayout(modifier) },

        ) {
        navController.popBackStack()
    }


}

@Composable
fun SupportCardLayout(modifier: Modifier) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        colors = CardDefaults.cardColors(containerColor = Cream)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Praesent pellentesque congue lorem, vel tincidunt tortor.",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Divider(color = Orange2)
            // List items
            HelpItem(title = "Help with the order", subtitle = "Support")
            Divider(color = Orange2)
            HelpItem(title = "Help center", subtitle = "General Information")
        }
    }
}

@Composable
fun HelpItem(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable { /* Navigate */ },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Icon(
            Icons.Default.ArrowForwardIos,
            contentDescription = "Next",
            tint = OrangeBase
        )
    }
}

