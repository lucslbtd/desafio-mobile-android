package com.example.desafiomarvel.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiomarvel.data.model.Character
import com.example.desafiomarvel.databinding.ItemCardCharacterBinding

class CharacterAdapter(var characters: MutableList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCardCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCardCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.tvCharactersName.text = character.name

        val thumbnail = "${character.thumbnail?.path}.${character.thumbnail?.extension}"
        Glide.with(holder.binding.root)
            .load(thumbnail)
            .centerCrop()
            .into(holder.binding.ivCharactersImage)
    }

    override fun getItemCount() = characters.size
}
