package com.messenger.core.theme.previews

import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

private const val LONG_DIMENSION = 800
private const val SHORT_DIMENSION = 400

@Preview(showBackground = true, widthDp = SHORT_DIMENSION, heightDp = LONG_DIMENSION)
@Preview(showBackground = true, widthDp = LONG_DIMENSION, heightDp = SHORT_DIMENSION)
@Preview(
    showBackground = true,
    widthDp = SHORT_DIMENSION,
    heightDp = LONG_DIMENSION,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    widthDp = LONG_DIMENSION,
    heightDp = SHORT_DIMENSION,
    uiMode = UI_MODE_NIGHT_YES
)
annotation class ScreenPreview