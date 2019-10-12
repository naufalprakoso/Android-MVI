package com.naufalprakoso.mvi.api

import androidx.lifecycle.LiveData
import com.naufalprakoso.mvi.model.Character
import com.naufalprakoso.mvi.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all.json")
    fun getAllCharacters(): LiveData<GenericApiResponse<List<Character>>>

    @GET("id/{characterId}.json")
    fun getCharacter(
        @Path("characterId") characterId: String
    ): LiveData<GenericApiResponse<Character>>
}