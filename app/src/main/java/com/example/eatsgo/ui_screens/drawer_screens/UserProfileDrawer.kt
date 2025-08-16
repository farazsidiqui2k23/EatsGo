package com.example.eatsgo.ui_screens.drawer_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.Firebase.userData
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2


@Composable
fun ProfileDrawerContent(modifier: Modifier = Modifier, userData: userData,onNavigate: (String) -> Unit) {

    var onItemClicked by remember{ mutableStateOf("") }

    LaunchedEffect(onItemClicked){
        when (onItemClicked) {
            "My Orders" -> onNavigate("orders_screen")
            "My Profile" -> onNavigate("profile_screen")
            "Delivery Address" -> onNavigate("address_screen")
            "Payment Methods" -> onNavigate("payment_screen")
            "Contact Us" -> onNavigate("contact_screen")
            "Help & FAQs" -> onNavigate("help_screen")
            "Settings" -> onNavigate("settings_screen")
            "Log Out" -> onNavigate("logout")
        }
    }




    val drawerItems = listOf(
        Pair(Icons.Default.ShoppingBag, "My Orders"),
        Pair(Icons.Default.Person, "My Profile"),
        Pair(Icons.Default.Home, "Delivery Address"),
        Pair(Icons.Default.Payment, "Payment Methods"),
        Pair(Icons.Default.Call, "Contact Us"),
        Pair(Icons.Default.Help, "Help & FAQs"),
        Pair(Icons.Default.Settings, "Settings"),
        Pair(Icons.Default.ExitToApp, "Log Out")
    )


    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start, modifier = Modifier
    ) {
        // Profile section
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.dummy_user),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    userData.name,
                    color = Cream,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)), maxLines = 1,
                    fontSize = 18.sp, overflow = TextOverflow.Ellipsis
                )
                Text(
                    userData.email, color = Cream, fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(drawerItems) { item ->
                DrawerItem(
                    icon = item.first,
                    text = item.second,
                    onClick = {name ->
                        onItemClicked = name
                    }
                )
                Divider(color = Orange2.copy(alpha = 0.4f))
            }
        }

    }
}


@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    onClick: (itemname : String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick(text) } // detect click
            .padding(vertical = 8.dp)
    ) {
        Icon(
            icon, contentDescription = text, tint = OrangeBase, modifier = Modifier
                .background(
                    Cream, RoundedCornerShape(12.dp)
                )
                .padding(6.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text,
            color = Yellow2,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}
