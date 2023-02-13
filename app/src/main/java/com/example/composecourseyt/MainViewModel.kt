package com.example.composecourseyt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // This is a cold flow, which means that this code does nothing if there
    // are no subscribers
    val countdownFlow = flow<Int> {
        val startingValue = 5
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
        // collectFlow()
        // collectFlow2()
        // collectFlow3()
        collectFlow4()
    }

    // collect, collectLatest, map, forEach, count
    private fun collectFlow() {
        // Could also be done like this
        /* countdownFlow.onEach {
             println(it)
         }.launchIn(viewModelScope)*/


        viewModelScope.launch {
            // this is executed every single emission of countdownFlow
            /*countdownFlow.collect { time ->
            println("The current time is: $time")
            }*/
            // This is also executed every single emission of countdownFlow,
            // BUT if the code inside the block is not finished and a new emission
            // is received, then the block will be canceled
            /*countdownFlow.collectLatest { time ->
                delay(1500L)
                println("The current time is: $time")
            }*/
            val count = countdownFlow.filter { time ->
                time % 2 == 0
            }
                // only uses filtered value now
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)

                }
                /*.collect { time ->
                    println("The current time is: $time")
                }*/
                // Will count values that match specific conditions
                .count {
                    it % 2 == 0
                }
            println("The count is $count")
        }
    }

    // reduce, fold
    private fun collectFlow2() {
        viewModelScope.launch {
            val reduceResult = countdownFlow
                // adds all values together
                /*.reduce { accumulator, value ->
                    accumulator + value
                }*/
                // same as reduce, but adds the initial value to final value
                .fold(100) { acc, value -> acc + value }
            println("Reduced value: $reduceResult")
        }
    }

    // flatMapConcat, flatMapMerge, flatMapLatest
    private fun collectFlow3() {
        // Example of flattening
        // [[1, 2], [1, 2, 3]] -> [1, 2, 1, 2, 3]

        val flow1 = flow {
            emit(1)
            delay(500L)
            emit(2)
        }

        viewModelScope.launch {
            // basically it takes the value of the first emit
            // so 1+1 = 2, 1+2 = 3
            // after delay, 2+1 = 3, 2+2 = 4
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("The value is $value")
            }

            // does all at once I guess? Not recommended to use
            flow1.flatMapMerge { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("Merge value is: $value")
            }

            // Same logic as collectLatest
            flow1.flatMapLatest { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("Merge value is: $value")
            }
        }
    }

    // onEach, buffer, conflate
    private fun collectFlow4() {
        // Let's say you go to a restaurant and you order
        // an appetizer, main course, dessert
        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main dish")
            delay(100L)
            emit("ice cream :D")
        }
        viewModelScope.launch {
            flow.onEach { println("FLOW: $it has been delivered") }
                // Buffer will make the flow continue even if collector isn't finished
                //.buffer()
                // Conflate will skip eating main dish, because after
                // eating the appetizer, we will eat the latest delivered meal,
                // which is the ice cream
                //.conflate()
                .collectLatest {
                    println("FLOW: now eating $it")
                    delay(1500L)
                    println("FLOW: finished eating $it")
                }
        }
    }
}