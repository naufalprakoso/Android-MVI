package com.naufalprakoso.mvi.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.naufalprakoso.mvi.R
import com.naufalprakoso.mvi.ui.detail.CharacterDetailActivity
import com.naufalprakoso.mvi.ui.main.state.MainStateEvent
import com.naufalprakoso.mvi.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var characterAdapter: MainCharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.setStateEvent(MainStateEvent.GetCharactersEvent)
        subscribeObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_characters.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            characterAdapter = MainCharacterAdapter {
                val intent = Intent(applicationContext, CharacterDetailActivity::class.java)
                intent.putExtra(Constants.CHARACTER_ID, it)
                startActivity(intent)
            }
            adapter = characterAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.characters?.let {
                        viewModel.setCharacterListData(it)
                    }
                }
            }
        })

        viewModel.viewState.observe(this, Observer { mainViewState ->
            mainViewState.characters?.let {
                characterAdapter.setCharacters(it)
                characterAdapter.notifyDataSetChanged()
            }
        })
    }
}
