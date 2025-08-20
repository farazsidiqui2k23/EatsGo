package com.example.eatsgo.ui_screens.profile_drawer_screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eatsgo.ui_screens.MainUILayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun OrderScreen(modifier: Modifier = Modifier, navController: NavController) {
    MainUILayout(
        modifier = modifier,
        title = "My Orders",
        changeTitle = "My Orders",
        content = { OrderCardLayout(modifier) },

    ){
        println("Back Pressed")
        navController.popBackStack()
    }


}

@Composable
fun OrderCardLayout(modifier: Modifier) {
    val orders = listOf(
        OrderItem(R.drawable.pizza, "Strawberry Shake", "$20.00", "29 Nov, 01:20 pm", "2 items"),
        OrderItem(R.drawable.pizza, "Chicken Burger", "$20.00", "17 Oct, 01:20 pm", "1 item"),
        OrderItem(R.drawable.pizza, "Sushi Wave", "$20.00", "22 Apr, 01:20 pm", "2 items"),
        OrderItem(R.drawable.pizza, "Pizza", "$20.00", "29 Nov, 01:20 pm", "1 item")
    )
    val Buttons = listOf("Active", "Completed", "Cancelled")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(20.dp, 20.dp),
        colors = CardDefaults.cardColors(
            Cream
        )
    ) {
        Column() {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(Buttons) { btn ->
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeBase),
                        shape = RoundedCornerShape(50), modifier = Modifier.width(112.dp)
                    ) {
                        Text(btn, fontSize = 12.sp, color = Cream)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }

            }
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(orders) { order ->
                    OrderCardItem(
                        order = order,
                        onCancel = { println("Cancelled: ${order.title}") },
                        onTrack = { println("Track: ${order.title}") }
                    )
                    Divider(color = Color.LightGray.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Composable
fun OrderCardItem(
    order: OrderItem,
    onCancel: () -> Unit,
    onTrack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = order.imageRes),
            contentDescription = order.title,
            modifier = Modifier
                .size(70.dp, 100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = order.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = order.dateTime,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = order.itemsCount,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeBase),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Cancel Order", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(6.dp))

                Button(
                    onClick = onTrack,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = OrangeBase,
                        containerColor = Orange2
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Track Driver", fontSize = 12.sp)
                }
            }
        }
    }
}

data class OrderItem(
    val imageRes: Int,
    val title: String,
    val price: String,
    val dateTime: String,
    val itemsCount: String
)

