package com.naufalprakoso.mvi.ui.detail.state

sealed class DetailStateEvent {

    class GetCharacterEvent(val characterId: Int) : DetailStateEvent()
    object None : DetailStateEvent()

}