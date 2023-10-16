package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdl.saluyusstoreapp.ui.theme.SaluyusStoreAppTheme
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@Composable
fun NavigationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            SearchBar(Modifier.fillMaxWidth())
        }
        Spacer(modifier = Modifier.width(8.dp))
        CartIcon()
        Spacer(modifier = Modifier.width(8.dp))
        ChatIcon()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier) {
    TextField(
        value = "", // Provide the value for the search text
        onValueChange = { /* Handle search text change */ },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(shape = RoundedCornerShape(4.dp), elevation = 2.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = TextTwo
            )
        },
        placeholder = {
            Text(text = "Cari ..", style = TextStyle(color = TextTwo))
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
    )
}

@Composable
fun CartIcon() {
    Icon(
        imageVector = Icons.Outlined.ShoppingBag,
        contentDescription = "Cart",
        modifier = Modifier.size(24.dp),
        tint = TextTwo
    )
}

@Composable
fun ChatIcon() {
    Icon(
        imageVector = Icons.Outlined.Chat,
        contentDescription = "Chat",
        modifier = Modifier.size(24.dp),
        tint = TextTwo
    )
}

@Composable
@Preview(showBackground = true)
fun NavigationSectionPreview() {
    SaluyusStoreAppTheme() {
        NavigationSection()
    }
}