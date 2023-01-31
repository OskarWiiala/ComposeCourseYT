package com.example.composecourseyt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            // with "= remember" you have to use textFieldState.value,
            // whereas with "by remember you can directly call the variable"
            var textFieldState by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            // Scaffold is a layout, will make it easy to include already existing design components
            // Such as top bars, navigation drawers and display snackbars
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) { it ->
                Log.d("padding", "$it")
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(horizontal = 16.dp)
                ) {
                    // If you want to customize your own text field, use BasicTextField instead
                    TextField(
                        value = textFieldState,
                        onValueChange = { textFieldState = it },
                        label = { Text(text = "Your name here") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        // the snackbar thingy requires a coroutine
                        scope.launch {
                            // Creates snackbar
                            scaffoldState.snackbarHostState.showSnackbar(message = "Hello $textFieldState")
                        }

                    }) {
                        Text(text = "Greet me")
                    }
                }
            }
        }
    }
}
