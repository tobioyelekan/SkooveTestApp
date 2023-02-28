package com.example.skoovetestapp.domain.model

data class SongItem(
    val id: Long,
    val title: String,
    val isFavorite: Boolean,
    val rating: Int,
    val audioUrl: String,
    val coverImgUrl: String,
    val duration: Long
)