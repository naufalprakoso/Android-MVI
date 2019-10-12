package com.naufalprakoso.mvi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Work(
    var occupation: String = "",
    var base: String = ""
) : Parcelable