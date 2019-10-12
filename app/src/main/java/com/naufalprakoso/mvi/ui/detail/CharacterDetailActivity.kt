package com.naufalprakoso.mvi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.naufalprakoso.mvi.R
import com.naufalprakoso.mvi.ui.detail.state.DetailStateEvent
import com.naufalprakoso.mvi.utils.Constants
import kotlinx.android.synthetic.main.activity_character_detail.*
import java.util.Locale

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val characterId = intent.getIntExtra(Constants.CHARACTER_ID, 1)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.setStateEvent(DetailStateEvent.GetCharacterEvent(characterId))
        subscribeObservers()

        img_back.setOnClickListener { finish() }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.character?.let {
                        viewModel.setCharacter(it)
                    }
                }
            }
        })

        viewModel.viewState.observe(this, Observer { mainViewState ->
            mainViewState.character?.let {
                txt_name.text = it.name
                txt_fullname.text = it.biography.fullName
                txt_race.text = it.appearance.race
                txt_height.text = it.appearance.height[1]
                txt_weight.text = it.appearance.weight[1]

                txt_aliases.text = it.biography.aliases[0]
                txt_place_birth.text = it.biography.placeOfBirth
                txt_alignment.text = it.biography.alignment.toUpperCase(Locale.getDefault())

                txt_intelligence.text = it.powerstats.intelligence.toString()
                txt_strength.text = it.powerstats.strength.toString()
                txt_speed.text = it.powerstats.speed.toString()
                txt_durability.text = it.powerstats.durability.toString()
                txt_power.text = it.powerstats.power.toString()
                txt_combat.text = it.powerstats.combat.toString()

                Glide.with(this).load(it.images.sm).into(img_hero)
            }
        })
    }
}
