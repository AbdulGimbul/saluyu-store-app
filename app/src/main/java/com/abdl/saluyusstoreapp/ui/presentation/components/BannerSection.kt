package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.Secondary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSection() {
    val pagerState = rememberPagerState()
    val sliderList = listOf(
        "https://fastly.picsum.photos/id/51/400/300.jpg?hmac=Er7ddl0a0PciY4DYlIM9AiacbF5qklteFpHyNnfizHY",
        "https://fastly.picsum.photos/id/416/400/300.jpg?hmac=fMeG-BxzT0ojvQ15TYlNAeuicWqcIZVO5wERblN3dRA",
        "https://fastly.picsum.photos/id/74/400/300.jpg?hmac=zzW2SeqM56tITdn6bJYOTpIfQX-4JOzIexT0jG7ISAw"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            pageCount = sliderList.size,
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier
                .height(200.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = sliderList[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderList.size) {
                val isActive = pagerState.currentPage == it
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(if (isActive) RoundedCornerShape(5.dp) else CircleShape)
                        .size(
                            width = if (isActive) 15.dp else 8.dp,
                            height = if (isActive) 8.dp else 8.dp
                        )
                        .background(Primary)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BannerSectionPreview() {
    SaluyusStoreAppTheme() {
        BannerSection()
    }
}