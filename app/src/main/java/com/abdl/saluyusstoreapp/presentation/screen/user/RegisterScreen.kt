package com.abdl.saluyusstoreapp.presentation.screen.user

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PhoneCallback
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.presentation.components.RoundedTextField
import com.abdl.saluyusstoreapp.presentation.navigation.Screen
import com.abdl.saluyusstoreapp.ui.theme.Field
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { navController.navigate(Screen.Login.route) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sign Up",
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
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
                    FullnameField()
                    Spacer(modifier = Modifier.height(18.dp))
                    RegisUsernameField()
                    Spacer(modifier = Modifier.height(18.dp))
                    RegisPasswordField()
                    Spacer(modifier = Modifier.height(18.dp))
                    PhoneNumberField()
                    Spacer(modifier = Modifier.height(18.dp))
                    AddressField()
                }
                SignUpButton()
                Spacer(modifier = Modifier.height(18.dp))
                SignInText(navController)
                Spacer(modifier = Modifier.weight(0.05f))
            }
        }
    }
}

@Composable
fun FullnameField() {
    var fullname by remember { mutableStateOf("") }
    RoundedTextField(
        value = fullname,
        onValueChange = { fullname = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Contacts,
                contentDescription = "Nama Lengkap",
                tint = TextTwo
            )
        },
        labelText = "Nama Lengkap"
    )
}

@Composable
fun RegisUsernameField() {
    var regisUsername by remember { mutableStateOf("") }
    RoundedTextField(
        value = regisUsername,
        onValueChange = { regisUsername = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Username",
                tint = TextTwo
            )
        },
        labelText = "Username"
    )
}

@Composable
fun RegisPasswordField() {
    var regisPassword by remember { mutableStateOf("") }
    RoundedTextField(
        value = regisPassword,
        onValueChange = { regisPassword = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password",
                tint = TextTwo
            )
        },
        labelText = "Password"
    )
}

@Composable
fun PhoneNumberField() {
    var phoneNumber by remember { mutableStateOf("") }
    RoundedTextField(
        value = phoneNumber,
        onValueChange = { phoneNumber = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "Phone Number",
                tint = TextTwo
            )
        },
        labelText = "Phone Number",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
}

@Composable
fun AddressField() {
    var address by remember { mutableStateOf("") }
    RoundedTextField(
        value = address,
        onValueChange = { address = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Address",
                tint = TextTwo
            )
        },
        labelText = "Address",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
        maxLine = 3
    )
}

@Composable
fun SignUpButton() {
    RoundedButton(
        onClick = { },
        text = "Sign Up",
    )
}

@Composable
fun SignInText(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Have an account? Sign in ",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            color = com.abdl.saluyusstoreapp.ui.theme.Text
        )
        Text(
            text = "here",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            color = Primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navController.navigate(Screen.Login.route) }
        )
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun RegisterPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}