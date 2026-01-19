package com.messenger.core.theme.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.core.theme.Dimens
import kotlin.math.max
import kotlin.random.Random

@Composable
fun AvatarImageView(
    name: String,
    imageSource: ImageSource,
    modifier: Modifier
) {
    if (imageSource is ImageSource.Empty) {
        FirstLetterAvatar(
            name = name,
            modifier = modifier
        )
    } else {
        ImageView(
            imageSource = imageSource,
            contentDescription = "Avatar of $name",
            modifier = modifier
        )
    }
}

@Composable
fun FirstLetterAvatar(
    name: String,
    modifier: Modifier = Modifier
) {
    val firstLetter = name.firstOrNull()?.uppercase() ?: "?"
    val isDarkMode = isSystemInDarkTheme()
    val color = Random(name.hashCode()).run {
        val nextColor: () -> Int = if (isDarkMode) ::nextLight else ::nextDark
        Color(nextColor(), nextColor(), nextColor())
    }
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(firstLetter)

    Canvas(
        modifier = modifier
            .background(
                color = color,
                shape = CircleShape
            )
    ) {
        val containerMinDimension = size.minDimension
        val textMaxDimension = textLayoutResult.size.run { max(width, height) }
        val scaleFactor = containerMinDimension / textMaxDimension

        scale(
            scale = scaleFactor * 0.7f
        ) {
            drawText(
                textLayoutResult = textLayoutResult,
                color = if (isDarkMode) Color.Black else Color.White,
                topLeft = Offset(
                    x = size.center.x - textLayoutResult.size.width / 2,
                    y = size.center.y - textLayoutResult.size.height / 2
                )
            )
        }
    }
}

private fun Random.nextDark(): Int {
    return nextInt(128)
}

private fun Random.nextLight(): Int {
    return nextInt(128, 256)
}

@Preview(showBackground = true)
@Composable
private fun FirstLetterAvatarPreview(name: String = "Violeta") {
    FirstLetterAvatar(
        name = name,
        modifier = Modifier.size(Dimens.MediumAvatarSize)
    )
}