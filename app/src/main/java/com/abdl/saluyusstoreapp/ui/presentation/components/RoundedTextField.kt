package com.abdl.saluyusstoreapp.ui.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation? = null,
    keyboardOptions: KeyboardOptions? = null,
    maxLine: Int = 1,
    isError: Boolean = false,
    enabled: Boolean = true
) {
    val passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .shadow(shape = RoundedCornerShape(8.dp), elevation = 2.dp).imePadding(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        textStyle = MaterialTheme.typography.bodySmall.copy(
            color = com.abdl.saluyusstoreapp.ui.theme.Text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(resId = R.font.lato_regular))
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        label = {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = TextTwo)) {
                        append(labelText)
                    }
                }
            )
        },
        visualTransformation = visualTransformation
            ?: if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions ?: KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = maxLine == 1,
        maxLines = maxLine,
        isError = isError,
        enabled = enabled,
    )
}