package com.example.composecourseyt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    // modifiers are applied sequentially. Padding also stacks
                    .border(5.dp, Color.Magenta) // border is pushed inward
                    .padding(5.dp)
                    .border(5.dp, Color.Blue)
                    .padding(5.dp)
                    .border(10.dp, Color.Red)
                    .padding(10.dp)
//                    .padding(top = 50.dp)
//                    .padding(16.dp) // Will push content into container
//                    .width(600.dp) // Will fill up to max width of screen
//                    .requiredWidth(600.dp) // Will fill beyond max width of screen
            ) {
                /*Text(
                    text = "text1", modifier = Modifier
                        // Acts much like margin, but won't push away other elements
                        // left, bottom
                        .offset(0.dp, 20.dp)
                )*/
                Text(
                    "text1", modifier = Modifier
                        .border(5.dp, Color.Yellow)
                        .padding(5.dp)
                        .offset(20.dp, 20.dp)
                        .border(10.dp, Color.Black)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "text2", modifier = Modifier
                    // clickable, zoomable, scrollable, draggable
                    .clickable { Log.d("test", "test") })
                Spacer(modifier = Modifier.height(50.dp))
                // horizontally drag
                var offsetX by remember { mutableStateOf(0f) }
                Text(
                    text = "text3", modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), 0) }
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                offsetX += delta
                            })
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("preview")
}