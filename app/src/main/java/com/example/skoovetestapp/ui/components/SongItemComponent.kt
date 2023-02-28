package com.example.skoovetestapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.skoovetestapp.ui.theme.SkooveTestAppTheme

@Composable
fun SongItemComponent(
    title: String,
    coverUrl: String,
    rating: Int,
    isFavorite: Boolean,
    onSongItemClicked: () -> Unit,
    onFavoriteClicked: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black)
            .clickable { onSongItemClicked() }
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                model = coverUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            RatingBar(
                modifier = Modifier.padding(10.dp),
                rating = rating
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Spacer(Modifier.weight(1f))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onFavoriteClicked()
                    },
                imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun PreviewSongItemComponent() {
    SkooveTestAppTheme {
        Box(Modifier.padding(10.dp)) {
            SongItemComponent(
                title = "song title",
                coverUrl = "https://nomad5.com/data/skoove/Waking_Me.png",
                rating = 3,
                isFavorite = false,
                onFavoriteClicked = {},
                onSongItemClicked = {}
            )
        }
    }
}