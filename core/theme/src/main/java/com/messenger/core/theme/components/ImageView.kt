package com.messenger.core.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.core.theme.R
import com.messenger.core.theme.Shapes

@Composable
fun ImageView(
    imageSource: ImageSource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    when (imageSource) {
        ImageSource.Empty -> {
            EmptyImageView()
        }

        is ImageSource.Remote -> {
            RemoteImageView(
                url = imageSource.url,
                modifier = modifier,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun RemoteImageView(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = url,
        modifier = modifier,
        contentDescription = contentDescription,
        error = {
            EmptyImageView(
                modifier = Modifier.matchParentSize(),
                contentDescription = contentDescription
            )
        }
    )
}

@Composable
fun EmptyImageView(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(R.drawable.ic_empty_image),
        contentDescription = contentDescription,
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = Shapes.MediumRoundedCornerShape
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ImageViewPreview() {
    ImageView(
        imageSource = ImageSource.Empty,
        modifier = Modifier.size(100.dp)
    )
}