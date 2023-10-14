package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@Composable
fun CartItem(
    itemId: Long,
    image: Int,
    itemName: String,
    itemPrice: Int,
    count: Int,
    onItemCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = itemName,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "12k Rupiah",
                color = TextTwo,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        }
        ItemCounter(orderId = itemId, orderCount = count, onProductIncreased = {}, onProductDecreased = {})
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview(){
    SaluyusStoreAppTheme {
        CartItem(
            itemId = 3,
            image = R.drawable.cat_sayur,
            itemName = "Sayur Bayam",
            itemPrice = 13000,
            count = 0,
            onItemCountChanged = {itemId, count -> }
        )
    }
}