package com.example.composecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // painterResource returns Painter, therefore the type is Painter
            val painter = painterResource(id = R.drawable.profile_picture)
            val description = "Profile picture"
            val title = "This is a profile picture"
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp)
            ) {
                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title
                )
            }

        }
    }
}

@Composable
// Composable functions ALWAYS start with a CAPITAL letter
fun ImageCard(
    // painter refers to the source of the image
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        // elevation is used for a shadow effect
        elevation = 5.dp
    ) {
        // items inside box will be stacked on top of each other
        Box(modifier = Modifier.height(175.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                // Will help scale image. Same as CenterCrop in XML.
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    // With brush, a gradient can be applied
                    // VerticalGradient starts from top to bottom?
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            // startY can also be calculated from the height of the box, but we use hardcoded values here
                            startY = 300f
                        )
                    )
            )
            // New box is made to align text to left. Otherwise everything would be aligned to left.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                // This will align the items (text) to bottom left
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    // fontSize preferably uses sp, because it scales with user's font size preference
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}