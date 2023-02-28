package com.example.skoovetestapp.ui.screens.all_songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.skoovetestapp.domain.model.SongItem
import com.example.skoovetestapp.ui.components.SkooveAppBar
import com.example.skoovetestapp.ui.components.SongItemComponent
import com.example.skoovetestapp.ui.theme.SkooveTestAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun AllSongScreen(
    viewModel: AllSongsViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.actionEvents.collect {
            when (it) {
                is EventActions.ShowMessage -> {
                    scope.launch {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                    }
                }
                is EventActions.ShowError -> {
                    errorMessage = it.message
                    showErrorDialog = true
                }
            }
        }
    }

    AllSongScreenContent(
        scaffoldState = scaffoldState,
        state = uiState,
        onSongItemClicked = { },
        onRefresh = { viewModel.onRefresh() },
        onFavoriteClicked = { songId, isFavorite ->
            viewModel.onFavoriteClicked(songId, isFavorite)
        }
    )

    if (showErrorDialog) {
        AlertDialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            onDismissRequest = {
                showErrorDialog = false
                errorMessage = ""
            },
            title = { Text(text = "Error!", style = MaterialTheme.typography.h1) },
            text = {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                Text(text = "Try Again", modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        showErrorDialog = false
                        viewModel.onRetryButtonClicked()
                    }
                )
            }
        )
    }
}

@Composable
fun AllSongScreenContent(
    scaffoldState: ScaffoldState,
    state: AllSongsViewState,
    onSongItemClicked: (Long) -> Unit,
    onFavoriteClicked: (Long, Boolean) -> Unit,
    onRefresh: () -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SkooveAppBar(title = "Skoovin", icon = null)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column {
                if (state.isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                SwipeRefresh(
                    state = rememberSwipeRefreshState(state.isRefreshing),
                    onRefresh = { onRefresh() }
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.padding(20.dp)
                    ) {
                        items(state.data) { songItem ->
                            SongItemComponent(title = songItem.title,
                                coverUrl = songItem.coverImgUrl,
                                rating = songItem.rating,
                                isFavorite = songItem.isFavorite,
                                onFavoriteClicked = {
                                    onFavoriteClicked(
                                        songItem.id,
                                        songItem.isFavorite
                                    )
                                },
                                onSongItemClicked = { onSongItemClicked(songItem.id) })
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAllSongScreenContentLoading() {
    SkooveTestAppTheme {
        AllSongScreenContent(
            scaffoldState = rememberScaffoldState(),
            state = AllSongsViewState(isLoading = true),
            onSongItemClicked = {},
            onFavoriteClicked = { a, b -> },
            onRefresh = {}
        )
    }
}

@Preview
@Composable
fun PreviewAllSongScreenContent() {
    SkooveTestAppTheme {
        AllSongScreenContent(
            scaffoldState = rememberScaffoldState(),
            state = AllSongsViewState(
                data = listOf(
                    SongItem(
                        id = 1L,
                        title = "some title",
                        isFavorite = false,
                        rating = 3,
                        audioUrl = "https://nomad5.com/data/skoove/Waking_Me.mp3",
                        coverImgUrl = "https://nomad5.com/data/skoove/Waking_Me.png",
                        duration = 1000L
                    ),
                    SongItem(
                        id = 1L,
                        title = "some title",
                        isFavorite = false,
                        rating = 3,
                        audioUrl = "https://nomad5.com/data/skoove/Waking_Me.mp3",
                        coverImgUrl = "https://nomad5.com/data/skoove/Waking_Me.png",
                        duration = 1000L
                    ),
                    SongItem(
                        id = 1L,
                        title = "some title",
                        isFavorite = false,
                        rating = 3,
                        audioUrl = "https://nomad5.com/data/skoove/Waking_Me.mp3",
                        coverImgUrl = "https://nomad5.com/data/skoove/Waking_Me.png",
                        duration = 1000L
                    )
                )
            ),
            onSongItemClicked = {},
            onRefresh = {},
            onFavoriteClicked = { _, _ -> }
        )
    }
}