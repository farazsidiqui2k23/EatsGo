@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.eatsgo.ui_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.Yellow2
import com.example.eatsgo.ui.theme.YellowBase
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerScreen(modifier: Modifier = Modifier, drawerState: Boolean, drawerContent : Int) {

    AnimatedVisibility(visible = drawerState) {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth())
        {
            Box(
                modifier
                    .fillMaxHeight()
                    .width(264.dp)
                    .background(
                        OrangeBase,
                        shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp)
                    )
                    .padding(22.dp)
            ) {

                ProfileDrawerContent(modifier)
            }
        }
    }
}


@Composable
fun ProfileDrawerContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start, modifier = Modifier
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
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 18.sp
                )
                Text(
                    "loremipsum@email.com", color = Cream, fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        DrawerItem(Icons.Default.ShoppingBag, "My Orders")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Person, "My Profile")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Home, "Delivery Address")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Payment, "Payment Methods")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Call, "Contact Us")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Help, "Help & FAQs")
        Divider(color = Orange2.copy(alpha = 0.4f))
        DrawerItem(Icons.Default.Settings, "Settings")
        Divider(color = Orange2.copy(alpha = 0.4f))
        Spacer(modifier = Modifier.height(6.dp))
        DrawerItem(Icons.Default.ExitToApp, "Log Out")

    }
}


@Composable
fun DrawerItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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

