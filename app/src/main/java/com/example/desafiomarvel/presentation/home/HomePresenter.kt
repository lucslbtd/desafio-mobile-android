package com.example.desafiomarvel.presentation.home

import com.example.desafiomarvel.domain.usecases.MarvelUseCase
import kotlinx.coroutines.*

class HomePresenter(
    private val view: HomeContract.View,
    private val marvelUseCase: MarvelUseCase
) : HomeContract.Presenter {

    private var offset = 0
    private val limit = 20
    val myHeroes = listOf(1009610, 1009368, 1009664, 1009351, 1009220)

    init {
        loadCharacters()
    }

    override fun loadCharacters() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val characters = marvelUseCase.getCharacters(offset, limit)

                if (characters.isSuccessful) {
                    view.showCharacters(characters.body()?.data?.results)
                    offset += limit
                } else {
                    view.showError("Carregando personagens. Aguarde")
                }
            } catch (e: Exception) {
                view.showError("Erro ao carregar personagens")
            }
        }
    }

    override fun loadCarouselCharacters() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val characterRequests = myHeroes.map { id ->
                    async {
                        marvelUseCase.getCharacterById(id)
                    }
                }

                val results = characterRequests.awaitAll()
                val famousCharacters =
                    results.mapNotNull { it.body()?.data?.results?.firstOrNull() }

                view.showCarouselCharacters(famousCharacters)
            } catch (e: Exception) {
                view.showError("Erro ao carregar carrossel.")
            }
        }
    }
}
