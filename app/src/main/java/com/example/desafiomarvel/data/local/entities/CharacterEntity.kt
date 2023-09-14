package com.example.desafiomarvel.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.desafiomarvel.data.model.CharacterDataWrapper

@Entity(tableName = "character_data")
data class CachedCharacterData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val offset: Int,
    val resultLimit: Int,
    val characterDataWrapper: CharacterDataWrapper
)


