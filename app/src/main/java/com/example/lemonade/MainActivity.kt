package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var squeezeTarget by remember { mutableStateOf((2..4).random()) }

    val imageRes = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textRes = when (step) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_lemonade
        else -> R.string.tap_empty_glass
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFFFF59D)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { //hola
        Text(
            text = stringResource(id = textRes),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    when (step) {
                        1 -> step = 2
                        2 -> {
                            if (squeezeCount < squeezeTarget - 1) {
                                squeezeCount++
                            } else {
                                step = 3
                                squeezeCount = 0
                            }
                        }
                        3 -> step = 4
                        else -> {
                            step = 1
                            squeezeTarget = (2..4).random()
                        }
                    }
                }
                .padding(16.dp)
        )
    }
}
