package com.naufalprakoso.mvi.repository

import androidx.lifecycle.LiveData
import com.naufalprakoso.mvi.api.MyRetrofitBuilder
import com.naufalprakoso.mvi.model.Character
import com.naufalprakoso.mvi.repository.NetworkBoundResource
import com.naufalprakoso.mvi.ui.main.state.MainViewState
import com.naufalprakoso.mvi.utils.ApiSuccessResponse
import com.naufalprakoso.mvi.utils.DataState
import com.naufalprakoso.mvi.utils.GenericApiResponse

object Repository {

    fun getAllCharacters(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<Character>, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Character>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        characters = response.body,
                        character = null
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Character>>> {
                return MyRetrofitBuilder.apiService.getAllCharacters()
            }

        }.asLiveData()
    }

    fun getCharacter(characterId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<Character, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<Character>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        characters = null,
                        character = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<Character>> {
                return MyRetrofitBuilder.apiService.getCharacter(characterId)
            }

        }.asLiveData()
    }
}




























