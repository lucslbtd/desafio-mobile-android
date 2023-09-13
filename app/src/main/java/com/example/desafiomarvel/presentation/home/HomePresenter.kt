package com.example.desafiomarvel.presentation.home

import android.widget.Toast
import com.example.desafiomarvel.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.*

class HomePresenter(
    private val view: HomeContract.View,
    private val getCharactersUseCase: GetCharactersUseCase
) : HomeContract.Presenter {

    private var offset = 0
    private val limit = 20

    init {
        loadCharacters()
    }


    override fun loadCharacters() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val characters = getCharactersUseCase.execute(offset, limit)

                if (characters.isSuccessful) {
                    view.showCharacters(characters.body()?.data?.results)
                    offset += limit
                } else {
                    view.showError("Carregando personagens. Aguarde")
                }
            } catch (e: Exception) {
                TODO("Criar casos de erro")
            }
        }
    }



}
