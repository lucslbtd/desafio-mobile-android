package com.example.desafiomarvel.presentation.home

import com.example.desafiomarvel.data.model.Character

interface HomeContract {
    interface View {
        fun showCharacters(characters: List<Character>?)
        fun showError(message: String)
    }

    interface Presenter {
        fun loadCharacters()
    }
}
