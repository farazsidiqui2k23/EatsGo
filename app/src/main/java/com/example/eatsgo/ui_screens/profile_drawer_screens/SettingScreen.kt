package com.example.eatsgo.ui_screens.profile_drawer_screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui_screens.MainUILayout


@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    MainUILayout(
        modifier = modifier,
        title = "My Orders",
        changeTitle = "My Orders",
        content = { SettingCardLayout(modifier) },

        ) {
        println("Back Pressed")
//        navController.popBackStack()
    }


}

@Composable
fun SettingCardLayout(modifier: Modifier) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        colors = CardDefaults.cardColors(containerColor = Cream)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            // List items
            SettingItem(title = "Edit Profile", subtitle = "Support")
            Divider(color = Orange2)
        }
    }
}

@Composable
fun SettingItem(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate */ },
        colors = CardDefaults.cardColors(Yellow2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Icon(
                Icons.Default.Edit,
                contentDescription = "Next",
                tint = OrangeBase
            )
        }
    }
}

@Preview
@Composable
private fun UI() {
    SettingScreen()
}