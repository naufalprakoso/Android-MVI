package com.naufalprakoso.mvi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PowerStats(
    var intelligence: Int = 0,
    var strength: Int = 0,
    var speed: Int = 0,
    var durability: Int = 0,
    var power: Int = 0,
    var combat: Int = 0
) : Parcelable