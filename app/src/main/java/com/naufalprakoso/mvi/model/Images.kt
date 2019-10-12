package com.naufalprakoso.mvi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    var xs: String = "",
    var sm: String = "",
    var md: String = "",
    var lg: String = ""
) : Parcelable