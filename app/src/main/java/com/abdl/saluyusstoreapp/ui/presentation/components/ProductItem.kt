package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@Composable
fun ProductItem(
    productItem: ProductItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(productItem.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(170.dp)
                .clip(MaterialTheme.shapes.extraSmall)
        )
        Text(
            text = productItem.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = com.abdl.saluyusstoreapp.ui.theme.Text,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = productItem.priceTag,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 14.sp,
                color = Primary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = productItem.soldOut,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 12.sp,
                color = TextTwo,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

data class ProductItem(
    val imageUrl: String, val title: String,
    val priceTag: String, val soldOut: String,
)

@Composable
@Preview(showBackground = true)
fun ProductReview() {
    SaluyusStoreAppTheme {
        ProductItem(
            productItem = ProductItem(
                imageUrl = "https://picsum.photos/400/400", title = "Produk 6",
                priceTag = "Rp. 143.000",
                soldOut = "36 Terjual"
            )
        )
    }
}