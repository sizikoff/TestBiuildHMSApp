package com.first.testbiuildhmsapp.model

data class ClientState(
    val clientName: String = "",
    val textColor: Long = 0xFF000000, // Цвет текста в формате Long (по умолчанию черный)
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
