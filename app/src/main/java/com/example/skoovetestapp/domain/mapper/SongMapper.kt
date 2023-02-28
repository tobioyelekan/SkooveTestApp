package com.example.skoovetestapp.domain.mapper

import com.example.skoovetestapp.data.cache.entity.SongDataEntity
import com.example.skoovetestapp.data.model.SongData
import com.example.skoovetestapp.domain.model.SongItem
import javax.inject.Inject

class SongMapper @Inject constructor() {
    fun mapEntityToItem(songDataEntity: SongDataEntity) = SongItem(
        id = songDataEntity.id,
        title = songDataEntity.title,
        isFavorite = songDataEntity.isFavorite,
        rating = songDataEntity.rating,
        audioUrl = songDataEntity.audioUrl,
        coverImgUrl = songDataEntity.coverImgUrl,
        duration = songDataEntity.duration
    )

    fun mapToEntity(songData: SongData) = SongDataEntity(
        title = songData.title,
        isFavorite = false,
        rating = 2,
        audioUrl = songData.audio,
        coverImgUrl = songData.cover,
        duration = songData.duration
    )
}