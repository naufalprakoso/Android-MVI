package com.naufalprakoso.mvi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.naufalprakoso.mvi.model.Character
import com.naufalprakoso.mvi.repository.Repository
import com.naufalprakoso.mvi.ui.detail.state.DetailStateEvent
import com.naufalprakoso.mvi.ui.detail.state.DetailViewState
import com.naufalprakoso.mvi.utils.AbsentLiveData
import com.naufalprakoso.mvi.utils.DataState

class DetailViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<DetailStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<DetailViewState> = MutableLiveData()

    val viewState: LiveData<DetailViewState>
        get() = _viewState

    val dataState: LiveData<DataState<DetailViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(stateEvent: DetailStateEvent): LiveData<DataState<DetailViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        return when (stateEvent) {
            is DetailStateEvent.GetCharacterEvent -> {
                Repository.getCharacter(stateEvent.characterId)
            }
            is DetailStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setCharacter(character: Character) {
        val update = getCurrentViewStateOrNew()
        update.character = character
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew(): DetailViewState {
        return viewState.value?.let {
            it
        } ?: DetailViewState()
    }

    fun setStateEvent(event: DetailStateEvent) {
        val state: DetailStateEvent = event
        _stateEvent.value = state
    }

}