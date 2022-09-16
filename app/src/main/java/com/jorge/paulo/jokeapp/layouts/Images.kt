package com.jorge.paulo.jokeapp.layouts

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.paulo.jokeapp.R


@Composable
fun Usage() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)) {
        OverlappingRow(
            overlapFactor = 0.7f
        ) {
            val images = intArrayOf(
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.imag3,
                R.drawable.img4
            )
            for (i in images.indices) {
                Image(
                    painter = painterResource(id = images[i]),
                    contentDescription = null,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .border(width = 1.dp, color = Color.White, shape = CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White),

                ) {
                Text(
                    text = "10+",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,

                    )
            }
        }
    }

}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.1, to = 1.0) overlapFactor: Float = 0.5f,
    content: @Composable () -> Unit,
) {
    val measurePolicy = overlappingRowMeasurePolicy(overlapFactor)
    Layout(
        measurePolicy = measurePolicy,
        content = content,
        modifier = modifier
    )
}


fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1, placeables.size)
        .sumOf { it.width } * overlapFactor + placeables[0].width).toInt()
    layout(width, height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}