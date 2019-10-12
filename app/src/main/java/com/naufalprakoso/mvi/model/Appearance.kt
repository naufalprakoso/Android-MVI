package com.naufalprakoso.mvi.model

data class Appearance(
    var gender: String = "",
    var race: String = "",
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String = "",
    var hairColor: String = ""
)