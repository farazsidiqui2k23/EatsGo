@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.eatsgo.ui_screens.drawer_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eatsgo.Firebase.userData
import com.example.eatsgo.ui.theme.OrangeBase

@Composable
fun NavigationDrawerScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    drawerState: Boolean,
    drawerContent: Int,
    userData: userData,
    swipeRight: (drawerState: Boolean) -> Unit
) {

    AnimatedVisibility(
        visible = drawerState,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth }, // start off-screen to the right
            animationSpec = tween(durationMillis = 300)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth }, // slide back to right
            animationSpec = tween(durationMillis = 300)
        )
    ) {
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
                    .swipeToClose { swipeRight(false) }
            ) {

                when (drawerContent) {
                    0 -> {
                        CartDrawerContent(modifier)
                    }

                    1 -> {
                        NotificationDrawerContent(modifier)
                    }

                    2 -> {
                        ProfileDrawerContent(modifier, userData) { navigateTo ->
                            println("navigate to order")
                            navController.navigate(navigateTo)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Modifier.swipeToClose(onSwipeLeft: () -> Unit): Modifier {
    return this.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            if (dragAmount > 50) { // dragged left
                onSwipeLeft()
            }
        }
    }
}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = { onClick() })
    }
}