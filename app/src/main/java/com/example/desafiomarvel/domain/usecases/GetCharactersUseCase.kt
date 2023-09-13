package com.example.desafiomarvel.domain.usecases

import com.example.desafiomarvel.data.model.CharacterDataWrapper
import com.example.desafiomarvel.data.repository.MarvelRepository
import retrofit2.Response

class GetCharactersUseCase(private val marvelRepository: MarvelRepository) {
    suspend fun execute(offset: Int, limit: Int): Response<CharacterDataWrapper> {
        return marvelRepository.getCharacters(offset, limit)
    }
}
