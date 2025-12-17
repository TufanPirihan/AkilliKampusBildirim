package com.tufanpirihan.akillikampusbildirim.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NotificationDetailScreen(
    navController: NavHostController,
    notificationId: String
) {
    // Geçici bir ekran, sonra detaylandıracağız
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Bildirim Detayı: $notificationId")
    }
}