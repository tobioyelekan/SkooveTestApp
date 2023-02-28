package com.example.skoovetestapp.data.repository

import com.example.skoovetestapp.domain.model.SongItem
import com.example.skoovetestapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getAllSongs(): Flow<Resource<List<SongItem>>>
    suspend fun getSongById(songId: Long): SongItem?
    suspend fun setSongAsFavoriteById(songId: Long, isFavorite: Boolean): Resource<Unit>
    suspend fun setSongRatingById(songId: Long, rating: Int): Resource<Unit>
}