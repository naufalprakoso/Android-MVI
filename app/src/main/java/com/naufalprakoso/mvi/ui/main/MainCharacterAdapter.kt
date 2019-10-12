package com.naufalprakoso.mvi.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufalprakoso.mvi.R
import com.naufalprakoso.mvi.model.Character
import kotlinx.android.synthetic.main.item_character.view.*

class MainCharacterAdapter(
    private val listener: (Character) -> Unit
) : RecyclerView.Adapter<MainCharacterAdapter.ViewHolder>() {

    private val characters: ArrayList<Character> = arrayListOf()

    fun setCharacters(characters: ArrayList<Character>) {
        this.characters.addAll(characters)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(characters[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(character: Character, listener: (Character) -> Unit) {
            itemView.txt_name.text = character.name
            itemView.txt_race.text = character.appearance.race

            itemView.setOnClickListener {
                listener(character)
            }
        }
    }

}