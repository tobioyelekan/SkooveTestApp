package com.example.skoovetestapp.data.source.remote

import com.example.skoovetestapp.data.model.SongApiResponse

interface RemoteDataSource {
    suspend fun getSkooveSongs(): SongApiResponse
}