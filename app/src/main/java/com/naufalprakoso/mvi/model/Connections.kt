package com.naufalprakoso.mvi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Connections(
    var groupAffiliation: String = "",
    var relatives: String = ""
) : Parcelable