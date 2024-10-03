package com.first.testbiuildhmsapp.viewmodel


import androidx.lifecycle.ViewModel

import com.first.testbiuildhmsapp.intent.ClientIntent
import com.first.testbiuildhmsapp.model.ClientState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ClientViewModel : ViewModel() {

    private val _state = MutableStateFlow(ClientState(clientName = "", textColor = 0x000000))
    val state: StateFlow<ClientState> = _state

    init {
        // Инициализируем состояние с данными клиента
        _state.value = ClientState(
            clientName = "OperatorC",
            textColor = 0x3357FF  // Задаем цвет
        )
    }

    fun handleIntent(intent: ClientIntent) {
        when (intent) {
            is ClientIntent.LoadClientData -> {
                // Здесь можно загружать реальные данные для клиента, если необходимо
            }
        }
    }
}