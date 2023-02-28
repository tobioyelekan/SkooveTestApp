package com.example.skoovetestapp.ui.screens.all_songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skoovetestapp.data.repository.SongRepository
import com.example.skoovetestapp.util.DispatcherProvider
import com.example.skoovetestapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSongsViewModel @Inject constructor(
    private val repository: SongRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(AllSongsViewState())
    val uiState = _uiState.asStateFlow()

    private val _actionEvents = MutableSharedFlow<EventActions>()
    val actionEvents = _actionEvents.asSharedFlow()

    init {
        getAllSongs()
    }

    private fun getAllSongs() {
        viewModelScope.launch(dispatchers.io) {
            repository.getAllSongs().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                data = result.data.orEmpty(),
                                isLoading = false,
                                isRefreshing = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false
                            )
                        }
                        _actionEvents.emit(EventActions.ShowError(result.message!!))
                    }
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = !_uiState.value.isRefreshing,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onRefresh() {
        if (!_uiState.value.isLoading) {
            _uiState.update { it.copy(isRefreshing = true) }
            getAllSongs()
        }
    }

    fun onFavoriteClicked(songId: Long, isFavorite: Boolean) {
        viewModelScope.launch(dispatchers.io) {
            when (val result = repository.setSongAsFavoriteById(songId, !isFavorite)) {
                is Resource.Success -> {
                    val message =
                        if (isFavorite) "Song removed as favorite" else "Song added as favorite"
                    _actionEvents.emit(EventActions.ShowMessage(message))
                }
                is Resource.Error -> _actionEvents.emit(EventActions.ShowError("Error occurred! ${result.message!!}"))
            }
        }
    }

    fun onRetryButtonClicked() {
        getAllSongs()
    }

}