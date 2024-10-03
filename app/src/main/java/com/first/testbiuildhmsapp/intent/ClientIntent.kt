package com.first.testbiuildhmsapp.intent

sealed class ClientIntent {
    object LoadClientData : ClientIntent()
}