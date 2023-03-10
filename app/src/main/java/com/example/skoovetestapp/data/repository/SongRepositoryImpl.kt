package com.example.skoovetestapp.data.repository

import com.example.skoovetestapp.data.source.local.SongLocalDataSource
import com.example.skoovetestapp.data.source.remote.RemoteDataSource
import com.example.skoovetestapp.domain.model.SongItem
import com.example.skoovetestapp.domain.mapper.SongMapper
import com.example.skoovetestapp.util.Resource
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.sql.SQLException
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val localDataSource: SongLocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val songMapper: SongMapper
) : SongRepository {
    override fun getAllSongs(): Flow<Resource<List<SongItem>>> = flow {
        emit(Resource.Loading(true))

        try {
            val response = remoteDataSource.getSkooveSongs()
            val entities = response.songData.map {
                songMapper.mapToEntity(it)
            }

            localDataSource.insertASongs(entities)

            localDataSource.getSongs().collect { dataFlow ->
                val songItems = dataFlow.map { songMapper.mapEntityToItem(it) }
                emit(Resource.Success(songItems))
            }
        } catch (e: Exception) {
            Timber.e(e)
            emit(Resource.Error("An error occurred! ${e.message}"))
            localDataSource.getSongs().collect { dataFlow ->
                val songItems = dataFlow.map { songMapper.mapEntityToItem(it) }
                emit(Resource.Success(songItems))
            }
        }
    }

    override suspend fun getSongById(songId: Long): SongItem? {
        val entity = localDataSource.getSongEntity(songId)
        return if (entity != null) songMapper.mapEntityToItem(entity) else null
    }

    override suspend fun setSongAsFavoriteById(songId: Long, isFavorite: Boolean): Resource<Unit> {
        val entity = localDataSource.getSongEntity(songId)
        return try {
            if (entity != null) {
                if (isFavorite) {
                    val existingFavoriteEntity =
                        localDataSource.getSongs().first().firstOrNull { it.isFavorite }
                    if (existingFavoriteEntity != null) {
                        localDataSource.updateSongData(existingFavoriteEntity.copy(isFavorite = false))
                    }

                    localDataSource.updateSongData(entity.copy(isFavorite = true))
                } else {
                    localDataSource.updateSongData(entity.copy(isFavorite = false))
                }
                Resource.Success(Unit)
            } else {
                Resource.Error("Song not found")
            }
        } catch (e: SQLException) {
            Resource.Error("Error occurred updating song ${e.message}")
        }
    }

    override suspend fun setSongRatingById(songId: Long, rating: Int): Resource<Unit> {
        val entity = localDataSource.getSongEntity(songId)
        return try {
            if (entity != null) {
                localDataSource.updateSongData(entity.copy(rating = rating))
                Resource.Success(Unit)
            } else {
                Resource.Error("Song not found")
            }
        } catch (e: SQLException) {
            Resource.Error("Error occurred updating song ${e.message}")
        }
    }
}