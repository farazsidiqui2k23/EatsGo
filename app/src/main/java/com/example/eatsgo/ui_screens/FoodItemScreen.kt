package com.example.eatsgo.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.Item
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.YellowBase

////@Composable
////fun FoodItemScreen(modifier: Modifier = Modifier, foodItem: Item) {
////
////    Box(
////        modifier
////            .fillMaxSize()
////            .background(YellowBase)
////    ) {
////        Column(modifier.fillMaxSize()) {
////            Box(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .fillMaxHeight(.15f)
////                    .padding(12.dp, 12.dp, 12.dp, 32.dp),
////                contentAlignment = Alignment.CenterStart
////            ) {
////                IconButton(onClick = {
////
////                }) {
////                    Icon(
////                        Icons.Default.ArrowBackIosNew,
////                        contentDescription = "",
////                        modifier = Modifier.size(30.dp),
////                        tint = OrangeBase
////                    )
////                }
////                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
////                    Text(
////                        text = "heading",
////                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
////                        fontSize = 26.sp,
////                        color = Cream
////                    )
////                }
////            }
////            Box(modifier = Modifier
////                .fillMaxSize()
////                .border(1.dp, Color.Red)){
//////                content()
////            }
////        }
////    }
////}
//
@Composable
fun FoodItemScreen(
    modifier: Modifier = Modifier,
    foodItem: Item,
    navController: NavController,
//    onAddToCart: () -> Unit = {}
) {
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    var count by rememberSaveable { mutableIntStateOf(1) }
    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(YellowBase, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            // 🔙 Back + Heading
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp, 12.dp, 32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp),
                        tint = OrangeBase
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = foodItem.name,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 20.sp,
                        color = OrangeBase
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton(onClick = { isFavourite = !isFavourite }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favourite",
                            modifier = Modifier.size(24.dp),
                            tint = if (isFavourite) OrangeBase else Orange2
                        )
                    }
                }
            }

            // 🖼️ Food Image
            Card(
                colors = CardDefaults.cardColors(Cream),
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedCornerShape(24.dp, 24.dp)
            ) {

                Column(modifier.padding(16.dp)) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1694579740719-0e601c5d2437?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                            contentDescription = foodItem.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Orange2)
                    // 💲 Price & Quantity
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${foodItem.price} Rs.",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 24.sp,
                            color = OrangeBase
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { count-- },
                                enabled = if (count > 1) true else false
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "Decrease",
                                    tint = if (count > 1) OrangeBase else Orange2
                                )
                            }
                            Text(
                                text = count.toString(),
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                color = Color.Black
                            )
                            IconButton(onClick = { count++ }) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Increase",
                                    tint = OrangeBase
                                )
                            }
                        }
                    }
                    Divider(color = Orange2)
                    Spacer(modifier = Modifier.height(12.dp))

                    // 📖 Description
                    Text(
                        text = foodItem.desc,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 14.sp,
                        color = Color.Gray,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 🛒 Add to Cart button
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeBase),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Add to Cart",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
