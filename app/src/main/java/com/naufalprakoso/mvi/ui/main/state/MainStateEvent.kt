package com.naufalprakoso.mvi.ui.main.state

sealed class MainStateEvent {

    class GetCharactersEvent: MainStateEvent()

    class GetCharacterEvent(
        val characterId: String
    ): MainStateEvent()

    class None: MainStateEvent()

}