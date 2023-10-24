package com.abdl.saluyusstoreapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdl.saluyusstoreapp.ui.presentation.navigation.NavigationItem
import com.abdl.saluyusstoreapp.ui.presentation.navigation.Screen
import com.abdl.saluyusstoreapp.ui.presentation.screen.product.CartItemScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.product.DashboardScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.product.DetailItemScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.GetStartedScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.LoginScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.ProfileScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.RegisterScreen
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaluyuStoreApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel = hiltViewModel(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute !in listOf(
                    Screen.Init.route,
                    Screen.Login.route,
                    Screen.Register.route,
                    Screen.DetailItem.route
                )
            ) {
                BottomBar(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Init.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Init.route) {
                GetStartedScreen(
                    navigateToRegister = { navController.navigate(Screen.Register.route) },
                    navigateToLogin = { navController.navigate(Screen.Login.route) },
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToRegister = { navController.navigate(Screen.Register.route) },
                    navigateToDashboard = { navController.navigate(Screen.Dashboard.route) },
                    navigateBack = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(navigateBack = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive = true
                        }
                    }
                })
            }
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    navigateToLogin = { navController.navigate(Screen.Login.route) }
                )
            }
            composable(Screen.DetailItem.route) {
                DetailItemScreen(
                    count = 0,
                    basePoint = 1,
                    navigateBack = { /*TODO*/ },
                    onAddCart = {}
                )
            }
            composable(Screen.CartItem.route) {
                CartItemScreen(onProductCountCanged = { _, _ -> }, onOrderButtonClicked = {})
            }
            composable(Screen.ProfileAccount.route) {
                ProfileScreen(navigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Beranda",
                icon = Icons.Outlined.Home,
                screen = Screen.Dashboard
            ),
            NavigationItem(
                title = "Telusuri",
                icon = Icons.Outlined.Explore,
                screen = Screen.DetailItem
            ),
            NavigationItem(
                title = "Disukai",
                icon = Icons.Outlined.FavoriteBorder,
                screen = Screen.Login
            ),
            NavigationItem(
                title = "Akun",
                icon = Icons.Outlined.AccountCircle,
                screen = Screen.ProfileAccount
            ),
        )
        NavigationBar {
            navigationItems.map { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    label = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}