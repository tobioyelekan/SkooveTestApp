package com.example.skoovetestapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongApiResponse(
    @Json(name = "data") val songData: List<SongData>
)

@JsonClass(generateAdapter = true)
data class SongData(
    @Json(name = "title") val title: String,
    @Json(name = "audio") val audio: String,
    @Json(name = "cover") val cover: String,
    @Json(name = "totalDurationMs") val duration: Long
)