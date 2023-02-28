package com.example.skoovetestapp.ui.screens.all_songs

import com.example.skoovetestapp.domain.model.SongItem

sealed interface AllSongUIState {
    object Loading : AllSongUIState
    class Success(val data: List<SongItem>) : AllSongUIState
}

data class AllSongsViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val data: List<SongItem> = emptyList()
)

sealed class EventActions {
    class ShowError(val message: String) : EventActions()
    class ShowMessage(val message: String) : EventActions()
}