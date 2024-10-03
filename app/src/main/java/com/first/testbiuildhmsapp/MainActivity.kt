package com.first.testbiuildhmsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.first.testbiuildhmsapp.intent.ClientIntent
import com.first.testbiuildhmsapp.viewmodel.ClientViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientScreen()
        }
    }
}

@Composable
fun ClientScreen(viewModel: ClientViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ClientIntent.LoadClientData)
    }

    // Преобразуем цвет из Long в Color
    val color = remember(state.textColor) {
        try {
            Color(android.graphics.Color.parseColor("#${state.textColor.toString(16).padStart(6, '0')}"))
        } catch (e: IllegalArgumentException) {
            Color.Red // Используем красный цвет, если ошибка
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = state.clientName,
            color = color, // Используем проверенный цвет
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewClientScreen() {
    ClientScreen()
}