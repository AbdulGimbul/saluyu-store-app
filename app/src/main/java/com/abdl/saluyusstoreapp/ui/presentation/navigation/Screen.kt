package com.abdl.saluyusstoreapp.ui.presentation.navigation

sealed class Screen(val route: String) {
    object Init : Screen("init")
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
    object DetailItem : Screen("detail_item")
    object CartItem : Screen("cart_item")
    object ProfileAccount : Screen("profile_account")
}