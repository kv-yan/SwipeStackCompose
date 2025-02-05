package com.example.app.card


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


@Composable
fun CardStackScreen() {
    val cards = remember {
        mutableStateListOf(
            Color(0xFFFF9800), // Orange
            Color(0xFF2196F3), // Blue
            Color(0xFF9C27B0), // Purple
            Color(0xFF4CAF50), // Green
            Color(0xFFFFEB3B)  // Yellow
        )
    }

    var swipeOffset by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd
    ) {
        cards.reversed()
            .forEachIndexed { index, color ->
                val offsetX = index * 35f
                val offsetY = index * 32

                if (index == cards.lastIndex) {
                    SwipableCard(
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .fillMaxWidth(0.7f)
                            .fillMaxHeight(0.4f), color = color, onSwipe = {
                            cards.add(cards.removeAt(0))
                            swipeOffset = 0f
                        }, offsetX = -(swipeOffset + offsetX), offsetY = offsetY
                    )
                } else {
                    StaticCard(
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .fillMaxWidth(0.7f)
                            .fillMaxHeight(0.4f), color = color, index = index, offsetX = -offsetX
                    )
                }
            }
    }
}