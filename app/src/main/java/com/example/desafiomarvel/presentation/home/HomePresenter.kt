package com.example.desafiomarvel.presentation.home

import android.util.Log
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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = marvelUseCase.getCharacters(offset, limit)

                if (characters.isSuccessful) {
                    view.showCharacters(characters.body()?.data?.results)
                    offset += limit
                } else {
                    when (characters.code()) {
                        204 -> view.showError("Erro: Personagens não encontrados.")

                        in 400..404 -> view.showError("Erro: Solicitação com problemas.")

                        500 -> view.showError("Erro interno do servidor. Tente novamente mais tarde.")
                    }
                }
            } catch (e: Exception) {
                Log.e("CatchError", "Erro forte: ${e.message}")
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
                if (characterRequests.isNotEmpty()) {
                    val results = characterRequests.awaitAll()
                    val famousCharacters =
                        results.mapNotNull { it.body()?.data?.results?.firstOrNull() }

                    view.showCarouselCharacters(famousCharacters)
                } else {
                    view.showError("Erro: Personagens não encontrados.")
                }
            } catch (e: Exception) {
                Log.e("CatchError", "Erro forte: ${e.message}")
            }
        }
    }
}
