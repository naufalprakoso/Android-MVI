package com.naufalprakoso.mvi.model

data class Biography(
    var fullName: String = "",
    var alterEgos: String = "",
    var aliases: List<String>,
    var placeOfBirth: String = "",
    var firstAppearance: String = "",
    var publisher: String = "",
    var alignment: String = ""
)