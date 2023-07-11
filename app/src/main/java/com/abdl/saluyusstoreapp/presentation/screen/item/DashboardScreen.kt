package com.abdl.saluyusstoreapp.presentation.screen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.presentation.components.BannerSection
import com.abdl.saluyusstoreapp.presentation.components.CategorySection
import com.abdl.saluyusstoreapp.presentation.components.NavigationSection
import com.abdl.saluyusstoreapp.presentation.components.ProductItem
import com.abdl.saluyusstoreapp.presentation.components.RecommendationSection
import com.abdl.saluyusstoreapp.presentation.components.ShapeItem
import com.abdl.saluyusstoreapp.ui.theme.Field

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(){
    Scaffold(
        modifier = Modifier
            .background(color = Field)
            .padding(16.dp),
        topBar = {
            TopAppBar(
                title = { /* Add your app title here */ },
                navigationIcon = {
                    NavigationSection()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(Field)
                .fillMaxWidth()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            BannerSection()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Kategori",
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            CategorySection(shapeItems = shapeItems)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Rekomendasi",
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            RecommendationSection(productItem = productItems, navigateToDetail = {})
        }
    }
}

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

val productItems = listOf(
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 1",
        priceTag = "Rp. 15.000",
        soldOut = "3 Terjual"
    ),
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 2",
        priceTag = "Rp. 19.000",
        soldOut = "332 Terjual"
    ),
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 3",
        priceTag = "Rp. 13.000",
        soldOut = "3 Terjual"
    ),
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 4",
        priceTag = "Rp. 63.000",
        soldOut = "32 Terjual"
    ),
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 5",
        priceTag = "Rp. 43.000",
        soldOut = "13 Terjual"
    ),
    ProductItem(
        imageUrl = "https://picsum.photos/400/400",
        title = "Produk 6",
        priceTag = "Rp. 143.000",
        soldOut = "36 Terjual"
    ),
)

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun PreviewDashboard(){
    DashboardScreen()
}