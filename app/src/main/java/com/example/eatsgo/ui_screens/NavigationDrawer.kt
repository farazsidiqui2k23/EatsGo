@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.eatsgo.ui_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerScreen(modifier: Modifier= Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Drawer content (shown when open)
    AnimatedVisibility(visible = true) {
        Box(
            modifier
                .fillMaxHeight()
                .width(260.dp)
                .background(
                    OrangeBase,
                    shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp)
                )
                .padding(16.dp)
        ) {

    }
}


@Composable
fun DrawerContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile section
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.dishoffer),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    "John Smith",
                    color = Cream,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp
                )
                Text("loremipsum@email.com", color = Cream, fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)))
            }
        }

        Divider(color = Color.White.copy(alpha = 0.4f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Shop, contentDescription = "text", tint = Color.White)
            Spacer(modifier = Modifier.width(12.dp))
            Text("text", color = Color.White, fontSize = 16.sp)
        }
//                    DrawerItem(Icons.Default.ShoppingCart, "My Orders")
//                    DrawerItem(Icons.Default.Person, "My Profile")
//                    DrawerItem(Icons.Default.Home, "Delivery Address")
//                    DrawerItem(Icons.Default.Payment, "Payment Methods")
//                    DrawerItem(Icons.Default.Call, "Contact Us")
//                    DrawerItem(Icons.Default.Help, "Help & FAQs")
//                    DrawerItem(Icons.Default.Settings, "Settings")
//                    DrawerItem(Icons.Default.ExitToApp, "Log Out")
    }
}
}

//@Composable
//fun DrawerItem(icon: , text: String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Icon(icon, contentDescription = text, tint = Color.White)
//        Spacer(modifier = Modifier.width(12.dp))
//        Text(text, color = Color.White, fontSize = 16.sp)
//    }
//}

@Preview(apiLevel = 34, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
private fun UI() {
   NavigationDrawerScreen()
}