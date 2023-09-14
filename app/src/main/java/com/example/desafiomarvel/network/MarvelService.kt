package com.example.desafiomarvel.network

import com.example.desafiomarvel.data.model.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharacterDataWrapper>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Int): Response<CharacterDataWrapper>
}
