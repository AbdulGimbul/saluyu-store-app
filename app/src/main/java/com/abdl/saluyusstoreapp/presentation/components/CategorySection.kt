package com.abdl.saluyusstoreapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.abdl.saluyusstoreapp.R

@Composable
fun CategorySection(shapeItems: List<ShapeItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(shapeItems) { item ->
            ShapeItem(item)
        }
    }
}


@Composable
fun ShapeItem(shapeItem: ShapeItem) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        ) {
            Image(
                painter = painterResource(shapeItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = shapeItem.label,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

data class ShapeItem(val imageUrl: Int, val label: String)

@Preview
@Composable
fun PreviewShapeRowExample() {
    val shapeItems = listOf(
        ShapeItem(
            imageUrl = R.drawable.cat_sayur,
            label = "Sayur"
        ),
        ShapeItem(
            imageUrl = R.drawable.cat_buah,
            label = "Buah"
        ),
        ShapeItem(
            imageUrl = R.drawable.cat_umbi,
            label = "Umbi"
        ),
        ShapeItem(
            imageUrl = R.drawable.cat_rempah,
            label = "Rempah"
        )
    )
    CategorySection(shapeItems)
}