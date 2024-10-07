package com.first.testbiuildhmsapp.viewmodel


import androidx.lifecycle.ViewModel

import com.first.testbiuildhmsapp.intent.ClientIntent
import com.first.testbiuildhmsapp.model.ClientState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ClientViewModel : ViewModel() {

    private val _state = MutableStateFlow(ClientState(clientName = "", textColor = 0x000000))
    val state: StateFlow<ClientState> = _state
    private val _latitude = MutableStateFlow(0.0)
    private val _longitude = MutableStateFlow(0.0)

    val latitude: StateFlow<Double> = _latitude
    val longitude: StateFlow<Double> = _longitude

    init {
        _state.value = ClientState(
            clientName = "OperatorA",
            textColor = 0xFF5733
        )
    }

    fun handleIntent(intent: ClientIntent) {
        when (intent) {
            is ClientIntent.LoadClientData -> {
            }
        }
    }

    // Функция обновления координат
    fun updateLocation(latitude: Double, longitude: Double) {
        _latitude.value = latitude
        _longitude.value = longitude
    }
}