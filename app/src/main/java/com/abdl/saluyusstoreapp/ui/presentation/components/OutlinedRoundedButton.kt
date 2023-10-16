package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.Primary

@Composable
fun OutlinedRoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Primary,
        ),
        border = BorderStroke(1.dp, color = Primary),
    ) {
        Text(
            text = text,
            modifier = modifier.padding(vertical = 8.dp),
            fontFamily = FontFamily(Font(resId = (R.font.lato_regular))),
            fontWeight = FontWeight.Bold
        )
    }
}