package com.naufalprakoso.mvi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable