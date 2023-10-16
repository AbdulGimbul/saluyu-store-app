package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.presentation.components.OutlinedRoundedButton
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.ui.theme.Field

@Composable
fun GetStartedScreen(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Field)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.saluyu),
                    contentDescription = "logo"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                GetStartedButton(navigateToLogin)
                Spacer(modifier = Modifier.height(8.dp))
                GetStartedSignUpButton(navigateToRegister)
            }
        }
    }
}

@Composable
fun GetStartedButton(navigateToLogin: () -> Unit) {
    RoundedButton(
        onClick = { navigateToLogin() },
        text = "Get Started",
    )
}

@Composable
fun GetStartedSignUpButton(navigateToRegister: () -> Unit) {
    OutlinedRoundedButton(
        onClick = { navigateToRegister() },
        text = "Sign Up",
    )
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun GetStartedPreview() {
    val navController = rememberNavController()
    GetStartedScreen(navigateToLogin = {}, navigateToRegister = {})
}