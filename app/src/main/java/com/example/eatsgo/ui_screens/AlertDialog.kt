@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("ComposePreviewMustBeTopLevelFunction")

package com.example.eatsgo.ui_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eatsgo.R
import com.example.eatsgo.ui.theme.Cream
import com.example.eatsgo.ui.theme.OrangeBase
import kotlinx.coroutines.delay

@Composable
fun AlertDialogBox(modifier: Modifier = Modifier, openDialog: Boolean) {
    BasicAlertDialog(
        onDismissRequest = {

        }
    ) {
        var iconVisibility  by remember{ mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(1000)
            iconVisibility = true
        }

        AnimatedVisibility(
            visible = openDialog,
            enter = slideInVertically(
                initialOffsetY = { it }, animationSpec = tween(400, easing = FastOutSlowInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it }, animationSpec = tween(400, easing = FastOutSlowInEasing)
            )
        ) {
            Card(colors = CardDefaults.cardColors(Cream)) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val scale by animateFloatAsState(
                        targetValue = if (iconVisibility) 1f else 0f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        label = ""
                    )
                    Icon(
                        Icons.Default.VerifiedUser,
                        contentDescription = "",
                        tint = Color(0xFF32cd32),
                        modifier = Modifier.size(50.dp).scale(scale)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Account Created Successfully",
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }
        }

    }
}

