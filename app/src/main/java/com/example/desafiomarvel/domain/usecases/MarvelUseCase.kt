package com.example.desafiomarvel.domain.usecases

import com.example.desafiomarvel.data.local.AppDatabase
import com.example.desafiomarvel.data.model.CharacterDataWrapper
import com.example.desafiomarvel.data.repository.MarvelRepository
import retrofit2.Response

class MarvelUseCase(
    private val marvelRepository: MarvelRepository
) {
    suspend fun getCharacters(offset: Int, limit: Int): Response<CharacterDataWrapper> {

        return marvelRepository.getCharacters(offset, limit)
    }

    suspend fun getCharacterById(characterId: Int): Response<CharacterDataWrapper> {
        return marvelRepository.getCharacterById(characterId)
    }
}
