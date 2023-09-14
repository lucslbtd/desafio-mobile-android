package com.example.desafiomarvel.data.local.databaseUtils

import androidx.room.TypeConverter
import com.example.desafiomarvel.data.model.CharacterDataWrapper
import com.google.gson.Gson

class CharacterDataWrapperConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCharacterDataWrapper(characterDataWrapper: CharacterDataWrapper?): String? {
        return gson.toJson(characterDataWrapper)
    }

    @TypeConverter
    fun toCharacterDataWrapper(json: String?): CharacterDataWrapper? {
        return gson.fromJson(json, CharacterDataWrapper::class.java)
    }
}
