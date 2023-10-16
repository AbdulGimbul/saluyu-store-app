package com.abdl.saluyusstoreapp.ui.presentation.screen.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.presentation.components.ItemCounter
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.Secondary
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItemScreen(
    count: Int,
    basePoint: Int,
    navigateBack: () -> Unit,
    onAddCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPoint by rememberSaveable {
        mutableStateOf(0)
    }
    var orderCount by rememberSaveable {
        mutableStateOf(count)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Handle back button click */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /* Handle favorite button click */ }) {
                            Icon(
                                Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = TextTwo
                            )
                        }
                        IconButton(onClick = { /* Handle share button click */ }) {
                            Icon(
                                Icons.Outlined.Share,
                                contentDescription = "Share",
                                tint = TextTwo
                            )
                        }
                        IconButton(onClick = { /* Handle cart button click */ }) {
                            Icon(
                                Icons.Outlined.ShoppingBag,
                                contentDescription = "Cart",
                                tint = TextTwo,
                            )
                        }
                    }
                },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues = it)
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/600/600"),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = (Modifier.height(16.dp)))
            Text(
                text = "Kategori",
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 12.sp,
                color = TextTwo,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Nama Barang",
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                color = com.abdl.saluyusstoreapp.ui.theme.Text,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Stok : Ada - Habis",
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 12.sp,
                color = TextTwo,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "Satuan",
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                color = com.abdl.saluyusstoreapp.ui.theme.Text,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(end = 16.dp),
                    elevation = ButtonDefaults.buttonElevation(2.dp)
                ) {
                    Text(text = "Kg")
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(end = 16.dp),
                    elevation = ButtonDefaults.buttonElevation(2.dp)
                ) {
                    Text(text = "Buah", color = TextTwo)
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(4.dp),
                    elevation = ButtonDefaults.buttonElevation(2.dp)
                ) {
                    Text(text = "Sisir", color = TextTwo)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Jumlah",
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                color = com.abdl.saluyusstoreapp.ui.theme.Text,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ItemCounter(
                orderId = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- })
            totalPoint = basePoint * orderCount
            Spacer(modifier = Modifier.weight(0.3f))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Harga",
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 12.sp,
                        color = TextTwo,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Rp. 50.000",
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        color = com.abdl.saluyusstoreapp.ui.theme.Text,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp),
                    modifier = Modifier.weight(0.25f),
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary)
                ) {
                    Icon(imageVector = Icons.Outlined.ShoppingBag, contentDescription = "Cart")
                }
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
                    modifier = Modifier.weight(0.45f),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text(text = "Order")
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun PreviewDetailItem() {
    SaluyusStoreAppTheme {
        DetailItemScreen(1, 500, navigateBack = {}, onAddCart = {})
    }
}