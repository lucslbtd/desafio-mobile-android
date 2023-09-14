package com.example.desafiomarvel.data.repository

import com.example.desafiomarvel.data.model.CharacterDataWrapper
import com.example.desafiomarvel.network.MarvelService
import retrofit2.Response

class MarvelRepository(private val marvelService: MarvelService) {
    suspend fun getCharacters(offset: Int, limit: Int): Response<CharacterDataWrapper> {
        return marvelService.getCharacters(offset, limit)
    }

    suspend fun getCharacterById(characterId: Int): Response<CharacterDataWrapper> {
        return marvelService.getCharacterById(characterId)
    }
}
