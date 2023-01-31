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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // For ConstraintSet, you need to create references for-
            // each composable we want to constrain in our layout
            val constraints = ConstraintSet {
                val greenBox = createRefFor(id = "greenbox")
                val redBox = createRefFor(id = "redbox")
                val guideLine = createGuidelineFromTop(0.5f)

                constrain(greenBox) {
                    // same as ConstrainTopToTopOf="parent" in XML
                    // top.linkTo(parent.top)
                    top.linkTo(guideLine)
                    // same as ConstrainStartToStartOf="parent" in XML
                    start.linkTo(parent.start)
                    // Dimension also has percentages, match parent, fill to constraints etc.
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(redBox) {
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end)
                    // if you want to stretch the red box between the green box and
                    // right side of parent, add this:
                    // width = Dimension.fillToConstraints
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
            }
            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.background(Color.Green).layoutId("greenbox"))
                Box(modifier = Modifier.background(Color.Red).layoutId("redbox"))
            }
        }
    }
}
