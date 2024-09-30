package com.example.danmed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.danmed.ui.screens.EntryForm
import com.example.danmed.ui.screens.EntryScreen
import com.example.danmed.ui.screens.StartScreen
import com.example.danmed.ui.theme.DanMedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DanMedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EntryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}