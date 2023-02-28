package com.example.skoovetestapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.skoovetestapp.data.cache.entity.SongDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveSongs(songs: List<SongDataEntity>)

    @Query("SELECT * FROM song_data_table")
    fun getSongs(): Flow<List<SongDataEntity>>

    @Query("SELECT * FROM song_data_table WHERE id=:id")
    suspend fun getSongEntity(id: Long): SongDataEntity?

    @Update
    fun updateSongData(song: SongDataEntity)

    @Query("DELETE FROM song_data_table")
    fun clearTable()
}