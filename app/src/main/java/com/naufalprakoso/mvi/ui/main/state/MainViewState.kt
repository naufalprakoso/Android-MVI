package com.naufalprakoso.mvi.ui.main.state

import com.naufalprakoso.mvi.model.Character

data class MainViewState(

    var characters: List<Character>? = null,
    var character: Character? = null

)