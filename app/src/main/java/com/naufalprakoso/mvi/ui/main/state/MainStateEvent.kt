package com.naufalprakoso.mvi.ui.main.state

sealed class MainStateEvent {

    class GetCharactersEvent: MainStateEvent()

    class None: MainStateEvent()

}