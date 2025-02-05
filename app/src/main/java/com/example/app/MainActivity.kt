package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app.card.CardStackScreen
import com.example.app.ui.theme.SwipeStackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeStackComposeTheme {
                CardStackScreen()
            }
        }
    }
}
