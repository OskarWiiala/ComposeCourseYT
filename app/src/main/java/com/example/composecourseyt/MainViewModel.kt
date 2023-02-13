package com.example.composecourseyt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // This is a cold flow, which means that this code does nothing if there
    // are no subscribers
    val countdownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue

        emit(startingValue)

        while (currentValue > 0) {
            delay(1000L)
            currentValue--

            // emit() is a function in FlowCollector
            // Whenever emit is called, the UI
            // (or some other part of code) will receive its value
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            // this is executed every single emission of countdownFlow
            /*countdownFlow.collect { time ->
            println("The current time is: $time")
            }*/
            // This is also executed every single emission of countdownFlow,
            // BUT if the code inside the block is not finished and a new emission
            // is received, then the block will be canceled
            countdownFlow.collectLatest { time ->
                delay(1500L)
                println("The current time is: $time")
            }
        }
    }
}