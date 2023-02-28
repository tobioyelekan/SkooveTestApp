package com.example.skoovetestapp.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_data_table")
data class SongDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val isFavorite: Boolean,
    val rating: Int,
    val audioUrl: String,
    val coverImgUrl: String,
    val duration: Long
)