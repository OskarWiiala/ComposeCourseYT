package com.example.composecourseyt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                // Three ways to save state:
                /*val mutableState = remember { mutableStateOf(default) }
                var value by remember { mutableStateOf(default) }
                val (value, setValue) = remember { mutableStateOf(default) }*/
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }

                ColorBox(
                    modifier = Modifier
                        .fillMaxSize()
                        // weight only possible in rows and columns
                        .weight(1f)
                ) {
                    // This lambda block is called when ColorBox is clicked
                    Log.d("color:", "$it")
                    color.value = it
                }
                Box(
                    modifier = Modifier
                        .background(color.value)
                        .weight(1f)
                        .fillMaxSize()
                )
            }


        }
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    // This is a callback function, which returns nothing, so Unit is used.
    updateColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        }
    )
}
