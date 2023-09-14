package com.example.desafiomarvel.data.model

data class CharacterDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: CharacterDataContainer?,
    val etag: String?
)

data class CharacterDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<Character>?
)

data class Character(
    val id: Int?,
    val name: String?,
    val thumbnail: Image?

)

data class Image(
    val path: String?,
    val extension: String?
)
