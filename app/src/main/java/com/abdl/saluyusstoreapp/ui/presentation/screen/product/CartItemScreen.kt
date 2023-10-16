package com.abdl.saluyusstoreapp.ui.presentation.screen.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.abdl.saluyusstoreapp.ui.presentation.components.CartItem
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.ui.theme.Field
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemScreen(
    onProductCountCanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Keranjang",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .background(Field)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    Column {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = TextTwo,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Diantar ke",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            modifier = Modifier.padding(start = 8.dp),
                            color = TextTwo,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Maleber, Kabupaten Kuningan, Jawa Barat",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 14.sp
                        )
                    }
                    Column {
                        Text(
                            text = "Edit",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            color = Primary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(arrayListOf(1, 2, 3)) {
                    CartItem(
                        itemId = 1,
                        image = R.drawable.cat_sayur,
                        itemName = "Huja",
                        itemPrice = 5000,
                        count = 4,
                        onItemCountChanged = { _, _ -> }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    // Subtotal row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Subtotal",
                            color = TextTwo,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Rp. 100.000",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            color = com.abdl.saluyusstoreapp.ui.theme.Text
                        )
                    }

                    // Second row and divider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ongkir",
                            color = TextTwo,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Rp. 10.000",
                            color = com.abdl.saluyusstoreapp.ui.theme.Text,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                        )
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    // Total row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total",
                            color = TextTwo,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Rp. 110.000",
                            color = com.abdl.saluyusstoreapp.ui.theme.Text,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                        )
                    }
                    OrderButton()
                }
            }
        }
    }
}

@Composable
fun OrderButton() {
    RoundedButton(
        onClick = { },
        text = "Order",
    )
}

@Preview(
    showBackground = true,
    device = Devices.DEFAULT
)
@Composable
fun CartItemScreenPreview() {
    SaluyusStoreAppTheme {
        CartItemScreen(onProductCountCanged = { _, _ -> }, onOrderButtonClicked = {})
    }
}