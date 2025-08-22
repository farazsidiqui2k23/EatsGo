package com.example.yourapp.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.eatsgo.R
import com.example.eatsgo.api_setup.data_class.TopDeal
import com.example.eatsgo.ui.theme.Brown
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.Orange2
import com.example.eatsgo.ui.theme.OrangeBase
import com.example.eatsgo.ui.theme.YellowBase
import kotlin.io.path.Path
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun TopDealScreen(
    modifier: Modifier = Modifier,
    topDeal: TopDeal,
    navController: NavController
) {
    var quantity by remember { mutableStateOf(1) }

    var isFavourite by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier
            .fillMaxSize()
            .background(YellowBase)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 🔙 Back Arrow + Heading
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.12f)
                    .padding(12.dp, 12.dp, 12.dp, 32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp),
                        tint = OrangeBase
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = topDeal.name,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 22.sp,
                        color = Cream
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton(onClick = { isFavourite = !isFavourite }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favourite",
                            modifier = Modifier.size(24.dp),
                            tint = if (isFavourite) OrangeBase else Orange2 // replace Orange2
                        )
                    }
                }
            }

            // 📸 Image + Discount Badge
            Card(
                colors = CardDefaults.cardColors(Cream),
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedCornerShape(24.dp, 24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            AsyncImage(
                                model = topDeal.pic,
                                contentDescription = topDeal.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        // 🔥 Discount Badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        ) {
                            DiscountBadge(discount = topDeal.discount)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 💵 Price Row
                    val discountPercent = topDeal.discount.replace("%", "").toIntOrNull() ?: 0
                    val originalPrice = topDeal.price
                    val discountedPrice =
                        (originalPrice - (originalPrice * discountPercent / 100.0)).roundToInt()
                    Divider(color = Orange2)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$$discountedPrice.00",
                            color = OrangeBase,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$$originalPrice.00",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        // ➕ ➖ Quantity
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { if (quantity > 1) quantity-- }) {
                                Text("-", fontSize = 22.sp, color = OrangeBase)
                            }
                            Text(
                                text = quantity.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            )
                            IconButton(onClick = { quantity++ }) {
                                Text("+", fontSize = 22.sp, color = OrangeBase)
                            }
                        }
                    }

                    Divider(color = Orange2)

                    Spacer(modifier = Modifier.height(12.dp))
                    // 📜 Description
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = topDeal.item,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 18.sp,
                            color = Brown
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = topDeal.desc,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
// 💰 Total & Discount
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Total Amount",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Brown
                        )
                        Text(
                            "${topDeal.price} Rs.",
                            color = Brown,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Discounted (${topDeal.discount})",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Brown
                        )
                        Text(
                            "${topDeal.price} Rs.",
                            color = OrangeBase,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                    // 🛒 Add to Cart Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { /* Add to Cart */ },
                            colors = ButtonDefaults.buttonColors(containerColor = OrangeBase),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier.fillMaxWidth(.6f)
                        ) {
                            Text("Add to Cart", color = Color.White, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DiscountBadge(discount: String, sizeDp: Int = 56) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(sizeDp.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = androidx.compose.ui.graphics.Path()
            val radius = size.minDimension / 2f
            val spikes = 12
            val angleStep = (2 * PI / spikes).toFloat()
            val innerRadius = radius * 0.64f

            path.moveTo(radius, 0f)
            for (i in 1 until spikes * 2) {
                val r = if (i % 2 == 0) radius else innerRadius
                val x = radius + r * cos(i * angleStep / 2)
                val y = radius + r * sin(i * angleStep / 2)
                path.lineTo(x, y)
            }
            path.close()
            drawPath(path = path, color = OrangeBase)
        }

        Text(
            text = "-$discount",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}