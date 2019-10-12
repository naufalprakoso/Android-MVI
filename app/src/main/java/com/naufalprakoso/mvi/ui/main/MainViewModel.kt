package com.naufalprakoso.mvi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.naufalprakoso.mvi.model.Character
import com.naufalprakoso.mvi.repository.Repository
import com.naufalprakoso.mvi.ui.main.state.MainStateEvent
import com.naufalprakoso.mvi.ui.main.state.MainViewState
import com.naufalprakoso.mvi.utils.AbsentLiveData
import com.naufalprakoso.mvi.utils.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    private val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        return when (stateEvent) {
            is MainStateEvent.GetCharactersEvent -> {
                Repository.getAllCharacters()
            }
            is MainStateEvent.GetCharacterEvent -> {
                Repository.getCharacter(stateEvent.characterId)
            }
            is MainStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setCharacterListData(characters: List<Character>) {
        val update = getCurrentViewStateOrNew()
        update.characters = characters
        _viewState.value = update
    }

    fun setCharacter(character: Character) {
        val update = getCurrentViewStateOrNew()
        update.character = character
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent) {
        val state: MainStateEvent = event
        _stateEvent.value = state
    }

}