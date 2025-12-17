package com.tufanpirihan.akillikampusbildirim.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("create_notification") {
            CreateNotificationScreen(navController = navController)
        }
        composable("notification_detail/{notificationId}") { backStackEntry ->
            val notificationId = backStackEntry.arguments?.getString("notificationId")
            NotificationDetailScreen(
                navController = navController,
                notificationId = notificationId ?: ""
            )
        }
    }
}