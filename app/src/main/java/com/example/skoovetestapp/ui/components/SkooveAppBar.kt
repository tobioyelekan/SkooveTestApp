package com.example.skoovetestapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SkooveAppBar(
    modifier: Modifier = Modifier,
    icon: ImageVector? = Icons.Filled.ArrowBack,
    tint: Color = Color.Black,
    textColor: Color = Color.Black,
    title: String = "",
    backgroundColor: Color = Color.Transparent,
    menuAction: (@Composable () -> Unit)? = null,
    onIconClicked: () -> Unit = { }
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = icon?.let {
            {
                Icon(
                    tint = tint,
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            onIconClicked()
                        }
                )
            }
        },
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = textColor
            )
        },
        backgroundColor = backgroundColor,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun PreviewAppBar() {
    SkooveAppBar(title = "App Bar")
}