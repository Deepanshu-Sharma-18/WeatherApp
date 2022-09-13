package com.example.weatherapplication.ui.theme.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapplication.R
import com.example.weatherapplication.ui.theme.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavController) {


    val animation = remember {
        mutableStateOf(false)
    }

    val textanimation = animateFloatAsState(
        targetValue = if (animation.value) 1F else 0.2F,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true){
        animation.value=true
        delay(2000)
        navController.popBackStack()
        navController.navigate(Screens.MainScreen.name+ "/pune")
    }

    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xF80F0346)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().scale(textanimation.value),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.weather_app),
                contentDescription = "image",
                modifier = Modifier.size(130.dp)
            )
        }
    }
}

