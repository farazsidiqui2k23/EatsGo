package com.example.eatsgo.ui_screens.drawer_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.YellowBase
import com.example.eatsgo.ui_screens.HomeScreen

@Composable
fun NotificationDrawerContent(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
    ) {
        // Profile section
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Cream, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(10.dp))

                Text(
                    "Notification",
                    color = Cream,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 22.sp
                )


        }
        Divider(color = YellowBase)
        LazyColumn { items(notifications){ notification ->

                Text(
                    text = notification,
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)), color = Cream
                )
            Divider(color = Orange2.copy(alpha = 0.4f))

        } }

    }
}
val notifications = mutableListOf(
    "Your order has been placed successfully.",
    "Your order is on the way. Track it now.",
    "Your package has been delivered.",
    "Payment confirmed. Thank you for shopping with us.",
    "Flash Sale! 50% off for the next 2 hours.",
    "We’ve added something new. Update your app to explore.",
    "Don’t forget to complete your profile for better recommendations.",
    "How was your recent purchase? Tap to rate us.",
    "New login detected from a different device.",
    "Password changed successfully. Was this you?",
    "Welcome aboard. Let's get started.",
    "Your cart is waiting. Checkout before it’s gone.",
    "Your subscription expires soon. Renew now to continue.",
    "You just reached a new level. Keep it up.",
    "App update available. Download the latest version now."
)

@Preview(apiLevel = 34, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
private fun UI() {
  HomeScreen()
}