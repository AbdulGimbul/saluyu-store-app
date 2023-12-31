package com.abdl.saluyusstoreapp.ui.presentation.screen.user

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abdl.saluyusstoreapp.AnimatedShimmer
import com.abdl.saluyusstoreapp.ui.presentation.common.UiState
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedButton
import com.abdl.saluyusstoreapp.ui.presentation.components.RoundedTextField
import com.abdl.saluyusstoreapp.ui.theme.Field
import com.abdl.saluyusstoreapp.ui.theme.Primary
import com.abdl.saluyusstoreapp.ui.theme.TextTwo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToLogin: () -> Unit,
    viewModel: UserViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (!isLoggedIn) {
            navigateToLogin()
            viewModel.resetUiState()
        }
    }

    var idUser by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }

    val uiStateGet = viewModel.uiStateGetUser.collectAsState().value

    when (uiStateGet) {
        is UiState.Idle -> {}

        is UiState.Loading -> {}

        is UiState.Success -> {
            idUser = uiStateGet.data.userId.toString()
            fullname = uiStateGet.data.fullName.toString()
            username = uiStateGet.data.username.toString()
            email = uiStateGet.data.email.toString()
            phoneNumber = uiStateGet.data.phoneNumber.toString()
            address = uiStateGet.data.address.toString()
            viewModel.resetUiState()
        }

        is UiState.Error -> {
            val errorMessage = uiStateGet.errorMessage
            LaunchedEffect(errorMessage) {
                scope.launch {
                    snackbarHostState.showSnackbar(errorMessage)
                }
            }
            viewModel.resetUiState()

            if (errorMessage == "JWT token already expired") {
                viewModel.logout()
            }
        }
    }

    val uiStateUpdate = viewModel.uiStateUpdateUser.collectAsState().value

    when (uiStateUpdate) {
        is UiState.Idle -> {}

        is UiState.Loading -> {}

        is UiState.Success -> {
            idUser = uiStateUpdate.data.userId.toString()
            fullname = uiStateUpdate.data.fullName.toString()
            username = uiStateUpdate.data.username.toString()
            email = uiStateUpdate.data.email.toString()
            phoneNumber = uiStateUpdate.data.phoneNumber.toString()
            address = uiStateUpdate.data.address.toString()
            LaunchedEffect(uiStateUpdate.data) {
                scope.launch {
                    snackbarHostState.showSnackbar("Update berhasil!")
                }
            }
            viewModel.resetUiState()
            viewModel.getUser()
        }

        is UiState.Error -> {
            val errorMessage = uiStateUpdate.errorMessage
            LaunchedEffect(errorMessage) {
                scope.launch {
                    snackbarHostState.showSnackbar(errorMessage)
                }
            }
            viewModel.resetUiState()
            viewModel.getUser()

            if (errorMessage == "JWT token already expired") {
                viewModel.logout()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.logout()
            }) {
                Icon(Icons.Default.Logout, contentDescription = "Logout")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(it)
                .background(color = Field),
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
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://wallpaperaccess.com/full/137507.jpg")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
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
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    if (isEditing) {
                        if (uiStateGet is UiState.Loading || uiStateUpdate is UiState.Loading) {
                            AnimatedShimmer()
                        } else {
                            ProfileFullnameField(
                                fullname = fullname,
                                onChanged = { value -> fullname = value },
                                enabled = true
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileUsernameField(
                                username = username,
                                onChanged = { value -> username = value },
                                enabled = true
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileEmailField(
                                email = email,
                                onChanged = { value -> email = value },
                                enabled = true
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfilePhoneNumberField(
                                phoneNumber = phoneNumber,
                                onChanged = { value -> phoneNumber = value },
                                enabled = true
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileAddressField(
                                address = address,
                                onChanged = { value -> address = value },
                                enabled = true
                            )
                            Spacer(modifier = Modifier.height(18.dp))

                        }
                    } else {
                        if (uiStateGet is UiState.Loading || uiStateUpdate is UiState.Loading) {
                            AnimatedShimmer()
                        } else {
                            ProfileFullnameField(
                                fullname = fullname,
                                onChanged = { value -> fullname = value },
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileUsernameField(
                                username = username,
                                onChanged = { value -> username = value })
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileEmailField(
                                email = email,
                                onChanged = { value -> email = value })
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfilePhoneNumberField(
                                phoneNumber = phoneNumber,
                                onChanged = { value -> phoneNumber = value })
                            Spacer(modifier = Modifier.height(18.dp))
                            ProfileAddressField(
                                address = address,
                                onChanged = { value -> address = value })
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
                }
                EditOrSaveButton(
                    isEditing = isEditing,
                    isLoading = uiStateGet is UiState.Loading || uiStateUpdate is UiState.Loading
                ) {
                    if (isEditing) {
                        Log.d("ProfileScreen", "cek isEditnya ya : $isEditing")
                        viewModel.updateUser(
                            idUser, fullname, username, email, phoneNumber, address
                        )
                    }

                    isEditing = !isEditing
                }
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun ProfileFullnameField(
    fullname: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    RoundedTextField(
        value = fullname,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Contacts,
                contentDescription = "Nama Lengkap",
                tint = TextTwo
            )
        },
        labelText = "Nama Lengkap",
        enabled = enabled
    )
}

@Composable
fun ProfileUsernameField(
    username: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    RoundedTextField(
        value = username,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Username",
                tint = TextTwo
            )
        },
        labelText = "Username",
        enabled = enabled
    )
}

@Composable
fun ProfilePasswordField(
    password: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val visualTransformation =
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    val toggleIcon =
        if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
    val contentDescription = if (passwordVisibility) "Show Password" else "Hide password"

    RoundedTextField(
        value = password,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password",
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
        enabled = enabled,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun ProfileEmailField(
    email: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    RoundedTextField(
        value = email,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email",
                tint = TextTwo
            )
        },
        labelText = "Email",
        enabled = enabled
    )
}

@Composable
fun ProfilePhoneNumberField(
    phoneNumber: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    RoundedTextField(
        value = phoneNumber,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "Phone Number",
                tint = TextTwo
            )
        },
        labelText = "Phone Number",
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Phone
        ),
        enabled = enabled
    )
}

@Composable
fun ProfileAddressField(
    address: String,
    onChanged: (String) -> Unit,
    enabled: Boolean = false,
) {
    RoundedTextField(
        value = address,
        onValueChange = { onChanged(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Address",
                tint = TextTwo
            )
        },
        labelText = "Address",
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
        ),
        maxLine = 3,
        enabled = enabled
    )
}

@Composable
fun EditOrSaveButton(isEditing: Boolean, isLoading: Boolean, onClick: () -> Unit) {
    RoundedButton(
        onClick = onClick,
        text = if (isEditing) "Save" else "Edit",
        isVisible = !isLoading
    )
}

@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navigateToLogin = {})
}