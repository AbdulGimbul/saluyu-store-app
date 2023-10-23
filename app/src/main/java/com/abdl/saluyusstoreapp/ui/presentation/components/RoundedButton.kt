package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.Primary

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .shadow(elevation = 8.dp, spotColor = Primary),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White,
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            modifier = modifier.padding(vertical = 8.dp),
            fontFamily = FontFamily(Font(resId = (R.font.lato_regular))),
            fontWeight = FontWeight.Bold
        )
    }
}