package com.naufalprakoso.mvi.ui.main.state

sealed class MainStateEvent {

    object GetCharactersEvent : MainStateEvent()
    object None : MainStateEvent()
}