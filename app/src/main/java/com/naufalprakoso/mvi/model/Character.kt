package com.naufalprakoso.mvi.model

data class Character(
    var id: Int = 0,
    var name: String = "",
    var slug: String = "",
    var powerstats: PowerStats,
    var appearance: Appearance,
    var biography: Biography,
    var work: Work,
    var connections: Connections,
    var images: Images
)