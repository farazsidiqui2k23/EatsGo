package com.example.eatsgo.ui_screens.drawer_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.YellowBase

@Composable
fun CartDrawerContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
    ) {
        // Profile section
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Notifications", tint = Cream, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                "Cart",
                color = Cream,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 22.sp
            )


        }
        Divider(color = YellowBase)
        Text(
            "You have 1 item in Cart",
            color = Cream,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 18.sp
        )
        CartItem()
        Divider(color = Orange2.copy(alpha = 0.4f))
        CartItem()
//        LazyColumn { items(notifications){ notification ->
//
//            Text(
//                text = notification,
//                modifier = Modifier.padding(0.dp, 8.dp),
//                fontFamily = FontFamily(Font(R.font.poppins_regular)), color = Cream
//            )
//            Divider(color = Orange2.copy(alpha = 0.4f))
//
//        } }

    }
}

@Composable
fun CartItem(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        Image(
            painter = painterResource(id = R.drawable.broast), // replace with your image
            contentDescription = "Product Image",
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Middle - Name and Price
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Half Broast",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 12.sp
            )
            Text(
                text = "Rs: 970",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Right - Date/Time and Quantity Controls
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "29/11/24",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 12.sp
            )
            Text(
                text = "15:00",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* decrease */ }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.White)
                }
                Text(
                    text = "2",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 14.sp
                )
                IconButton(onClick = { /* increase */ }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.White)
                }
            }
        }
    }
}


