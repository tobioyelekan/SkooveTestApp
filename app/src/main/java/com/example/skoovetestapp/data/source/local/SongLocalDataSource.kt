package com.example.skoovetestapp.data.source.local

import com.example.skoovetestapp.data.cache.entity.SongDataEntity
import kotlinx.coroutines.flow.Flow

interface SongLocalDataSource {
    suspend fun insertASongs(songs: List<SongDataEntity>)
    fun getSongs(): Flow<List<SongDataEntity>>
    suspend fun getSongEntity(id: Long): SongDataEntity?
    suspend fun updateSongData(song: SongDataEntity)
}