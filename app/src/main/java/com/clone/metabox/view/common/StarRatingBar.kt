package com.clone.metabox.view.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.clone.metabox.R

@Composable
fun StarRatingBar (
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp,
    fillStar: ImageBitmap,
    emptyStar: ImageBitmap
) {
    val totalCount = 5

    val height = LocalDensity.current.run { emptyStar.height.toDp() }
    val width = LocalDensity.current.run { emptyStar.width.toDp() }
    val space = LocalDensity.current.run { spaceBetween.toPx() }
    val totalWidth = width * totalCount + spaceBetween * (totalCount - 1)

    Box(
        modifier
            .width(totalWidth)
            .height(height)
            .drawBehind {
                drawRating(rating, emptyStar, fillStar, space)
            })
}

private fun DrawScope.drawRating (
    rating: Float,
    emptyStar: ImageBitmap,
    fillStar: ImageBitmap,
    space: Float
) {
    val totalCount = 5

    val imageWidth = emptyStar.width.toFloat()
    val imageHeight = size.height

    val reminder = rating - rating.toInt()
    val ratingInt = (rating - reminder).toInt()

    for (i in 0 until totalCount) {
        val start = imageWidth * i + space * i

        drawImage(
            image = emptyStar,
            topLeft = Offset(start, 0f)
        )
    }

    drawWithLayer {
        for (i in 0 until totalCount) {
            val start = imageWidth * i + space * i
            // Destination
            drawImage(
                image = fillStar,
                topLeft = Offset(start, 0f)
            )
        }

        val end = imageWidth * totalCount + space * (totalCount - 1)
        val start = rating * imageWidth + ratingInt * space
        val size = end - start

        // Source
        drawRect(
            Color.Transparent,
            topLeft = Offset(start, 0f),
            size = Size(size, height = imageHeight),
            blendMode = BlendMode.SrcIn
        )
    }


}

private fun DrawScope.drawWithLayer(block: DrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}