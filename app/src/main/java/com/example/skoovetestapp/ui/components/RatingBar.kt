package com.example.skoovetestapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    tintColor : Color = Color.White,
    size: Dp = 24.dp,
    shouldInteract: Boolean = false,
    onClicked: (Int) -> Unit = {}
) {
    var rate by remember { mutableStateOf(rating) }

    Box(modifier = modifier) {
        LazyRow {
            items(5) {
                Icon(
                    if (it < rating) Icons.Filled.Star else Icons.Filled.StarOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .clickable(shouldInteract) {
                            rate += 1
                            onClicked(rate)
                        },
                    tint = tintColor,
                )
            }
        }
    }
}

@Composable
@Preview
fun RatingBarPreview() {
    RatingBar(rating = 3)
}