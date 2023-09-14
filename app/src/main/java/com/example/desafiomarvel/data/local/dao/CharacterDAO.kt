package com.example.desafiomarvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafiomarvel.data.local.entities.CachedCharacterData

@Dao
interface CachedCharacterDataDao {
    @Query("SELECT * FROM character_data WHERE offset = :offset AND resultLimit = :resultLimit")
    fun getCachedCharacterData(offset: Int, resultLimit: Int): CachedCharacterData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCachedCharacterData(cachedCharacterData: CachedCharacterData)
}



