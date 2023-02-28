package com.example.skoovetestapp.data.source.remote

import com.example.skoovetestapp.data.api.SkooveApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: SkooveApiService
) : RemoteDataSource {
    override suspend fun getSkooveSongs() = withContext(Dispatchers.IO) {
        apiService.getSongList()
    }
}