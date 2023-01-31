package com.example.composecourseyt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // only required if using regular Column to scroll
            // Do not use regular column as scroll unless there are only a few items
            // val scrollState = rememberScrollState()
            // also, if using regular column to scroll, remember to use this modifier:
            // modifier = Modifier.verticalScroll(scrollState)

            // Use LazyColumn to scroll many items. It is very similar to RecyclerView in XML
            LazyColumn {
                itemsIndexed(
                    listOf(
                        "this",
                        "is",
                        "Jetpack",
                        "Compose",
                        "this",
                        "is",
                        "Jetpack",
                        "Compose",
                        "this",
                        "is",
                        "Jetpack",
                        "Compose",
                        "this",
                        "is",
                        "Jetpack",
                        "Compose",
                        "this",
                        "is",
                        "Jetpack",
                        "Compose"
                    )
                ) { index, string ->
                    Text(
                        text = string,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
                items(5000) {
                    /*Text(
                        // it refers to the item, which is an Int.
                        // Might as well consider it the index
                        text = "Item $it",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )*/
                }
            }
        }
    }
}
