package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdl.saluyusstoreapp.R
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedTextField
import com.abdl.saluyusstoreapp.ui.theme.Field
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.TextTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { navigateBack() }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Profile",
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
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        // Placeholder image or loaded profile image
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 4.dp)
                            .background(Primary, CircleShape)
                            .clickable { /* Handle change image click */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Change Image",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    ProfileFullnameField()
                    Spacer(modifier = Modifier.height(18.dp))
                    ProfileRegisUsernameField()
                    Spacer(modifier = Modifier.height(18.dp))
                    ProfileRegisPasswordField()
                    Spacer(modifier = Modifier.height(18.dp))
                    ProfilePhoneNumberField()
                    Spacer(modifier = Modifier.height(18.dp))
                    ProfileAddressField()
                }
                EditButton()
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun ProfileFullnameField() {
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
fun ProfileRegisUsernameField() {
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
fun ProfileRegisPasswordField() {
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
fun ProfilePhoneNumberField() {
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
fun ProfileAddressField() {
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        maxLine = 3
    )
}

@Composable
fun EditButton() {
    RoundedButton(
        onClick = { },
        text = "Edit",
    )
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navigateBack = {})
}