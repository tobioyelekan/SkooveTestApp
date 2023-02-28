package com.example.skoovetestapp.data.source.local

import com.example.skoovetestapp.data.cache.dao.SongDataDao
import com.example.skoovetestapp.data.cache.entity.SongDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongLocalDataSourceImpl @Inject constructor(
    private val songDataDao: SongDataDao
) : SongLocalDataSource {
    override fun getSongs(): Flow<List<SongDataEntity>> = songDataDao.getSongs()

    override suspend fun insertASongs(songs: List<SongDataEntity>) {
        withContext(Dispatchers.IO) {
            if (songs.isNotEmpty())
                songDataDao.clearTable()
            songDataDao.saveSongs(songs)
        }
    }

    override suspend fun getSongEntity(id: Long): SongDataEntity? {
        return withContext(Dispatchers.IO) {
            songDataDao.getSongEntity(id)
        }
    }

    override suspend fun updateSongData(song: SongDataEntity) {
        withContext(Dispatchers.IO) {
            songDataDao.updateSongData(song)
        }
    }

}