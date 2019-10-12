package com.naufalprakoso.mvi.ui

import com.naufalprakoso.mvi.utils.DataState

interface DataStateListener {
    fun onDataStateChange(dataState: DataState<*>?)
}