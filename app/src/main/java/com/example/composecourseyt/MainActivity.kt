package com.example.composecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Prevents components from being on top of each other
            Column {
                Column(
                    modifier = Modifier
                        .height(Dp(300f)) // one way of doing Dp
                        .width(300.dp) // another way of doing Dp
                        .background(Color.Green),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text("Hello")
                    Text("Hello2")
                    Text("World")
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize() // Fills all the space. You can also fraction fillMaxSize(0.5f)
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Hello")
                    Text("Hello2")
                    Text("World")
                }
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