package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.di.Injection
import com.abdl.saluyusstoreapp.ui.presentation.common.UiState
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedTextField
import com.abdl.saluyusstoreapp.ui.presentation.navigation.Screen
import com.abdl.saluyusstoreapp.ui.theme.Field
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.TextTwo
import com.abdl.saluyusstoreapp.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                navController.navigate(Screen.Dashboard.route)
            }
            is UiState.Error -> {
                Toast.makeText(LocalContext.current, "${uiState.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { navController.navigate(Screen.Init.route) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sign In",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Field)
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    UsernameField(username) { newUsername ->
                        username = newUsername
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    PasswordField(password) { newPassword ->
                        password = newPassword
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    ForgotPasswordText()
                }
                SignInButton(viewModel, username, password)

                Spacer(modifier = Modifier.height(18.dp))

                SignUpText(navController)
                Spacer(modifier = Modifier.weight(0.05f))
            }
        }
    }
}

@Composable
fun UsernameField(username: String, onUsernameChanged: (String) -> Unit) {
//    var username by remember { mutableStateOf("") }

    RoundedTextField(
        value = username,
        onValueChange = { onUsernameChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Username",
                tint = TextTwo
            )
        },
        labelText = "Username",
    )
}

@Composable
fun PasswordField(password: String, onPasswordChanged: (String) -> Unit) {
//    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val visualTransformation =
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    val toggleIcon =
        if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
    val contentDescription = if (passwordVisibility) "Show Password" else "Hide password"

    RoundedTextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock",
                tint = TextTwo
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = toggleIcon,
                    contentDescription = contentDescription,
                    tint = TextTwo
                )
            }
        },
        labelText = "Password",
        visualTransformation = visualTransformation,
    )
}

@Composable
fun ForgotPasswordText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Lupa Password?",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            color = Primary
        )
    }
}

@Composable
fun SignInButton(viewModel: LoginViewModel, username: String, password: String) {
    RoundedButton(
        onClick = {
            viewModel.login(username = username, password = password)
        },
        text = "Sign in",
    )
}

@Composable
fun SignUpText(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Not have an account? Sign up ",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            color = com.abdl.saluyusstoreapp.ui.theme.Text
        )
        Text(
            text = "here",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            color = Primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navController.navigate(Screen.Register.route) }
        )
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
//    LoginScreen(navController)
}