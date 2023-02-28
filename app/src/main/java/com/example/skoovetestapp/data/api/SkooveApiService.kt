package com.example.skoovetestapp.data.api

import com.example.skoovetestapp.data.model.SongApiResponse
import retrofit2.http.GET

interface SkooveApiService {
    @GET("https://nomad5.com/data/skoove/manifest.json")
    suspend fun getSongList(): SongApiResponse
}