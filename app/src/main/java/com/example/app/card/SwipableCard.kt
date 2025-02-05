package com.example.app.card


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun SwipableCard(
    modifier: Modifier,
    color: Color,
    onSwipe: () -> Unit,
    offsetX: Float,
    offsetY: Int,
) {
    var dragAmount by remember { mutableStateOf(0f) }

    Card(
        modifier = modifier
            .offset { IntOffset((offsetX + dragAmount).toInt(), offsetY) }
            .graphicsLayer(
                rotationZ = dragAmount / 20, // Rotate only during swipe
                translationX = dragAmount
            )
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (dragAmount > 150 || dragAmount < -150) {
                            onSwipe() // Remove the first card
                        }
                        dragAmount = 0f // Reset position
                    }
                ) { _, distance ->
                    dragAmount += distance
                }
            },
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, Color.White,),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            CardBottomSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}