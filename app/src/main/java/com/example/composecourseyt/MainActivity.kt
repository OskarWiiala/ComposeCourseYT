package com.example.composecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecourseyt.ui.theme.ComposeCourseYTTheme

class MainActivity : ComponentActivity() {

    // in XML
    // private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // in XML
        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.state.STARTED) {
                viewModel.stateFlow.collectLatest { number ->
                    binding.tvCounter.text = number.toString()
                }
            }
        }*/
        // Easier in XML when using the created function
        /*collectLatestLifecycleFlow(viewModel.stateFlow) { number ->
            binding.tvCounter.text = number.toString()
        }*/
        setContent {
            ComposeCourseYTTheme {
                val viewModel = viewModel<MainViewModel>()
                val time = viewModel.countdownFlow.collectAsState(initial = 10)
                val count = viewModel.stateFlow.collectAsState(initial = 0)

                LaunchedEffect(key1 = true) {
                    viewModel.sharedFlow.collect { number ->

                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    // used for countdown
                    /*Text(
                        text = time.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )*/
                    Button(onClick = { viewModel.incrementCounter() }) {
                        Text(text = "Counter: ${count.value}")

                    }
                }
            }
        }
    }
}
// would be AppCompatActivity in XML
/*
fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest()
        }
    }
}*/

/*
fun <T> ComponentActivity.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect()
        }
    }
}*/
