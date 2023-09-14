package com.example.desafiomarvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.desafiomarvel.data.local.dao.CachedCharacterDataDao
import com.example.desafiomarvel.data.local.databaseUtils.CharacterDataWrapperConverter
import com.example.desafiomarvel.data.local.entities.CachedCharacterData

@Database(entities = [CachedCharacterData::class], version = 1, exportSchema = false)
@TypeConverters(CharacterDataWrapperConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cachedCharacterDataDao(): CachedCharacterDataDao
}
