package com.tufanpirihan.akillikampusbildirim.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tufanpirihan.akillikampusbildirim.model.Notification
import com.tufanpirihan.akillikampusbildirim.viewmodel.NotificationViewModel
import com.tufanpirihan.akillikampusbildirim.model.NotificationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: NotificationViewModel = viewModel()
) {
    val notifications by viewModel.notifications.collectAsState(initial = emptyList())
    val searchQuery by viewModel.searchQuery.collectAsState(initial = "")
    val selectedFilter by viewModel.selectedFilter.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Bildirimler",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF141414),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { /* Profil */ }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Profil",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("create_notification") },
                containerColor = Color(0xFF2979FF),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Yeni Bildirim")
            }
        },
        containerColor = Color(0xFF050505)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Arama Kutusu
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Bildirim ara...", color = Color(0xFF888888)) },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Ara",
                        tint = Color(0xFF888888)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2979FF),
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Filtre Butonları
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedFilter == null,
                    onClick = { viewModel.updateFilter(null) },
                    label = { Text("Tümü") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2979FF),
                        selectedLabelColor = Color.White
                    )
                )
                FilterChip(
                    selected = selectedFilter?.name == "HEALTH",
                    onClick = { viewModel.updateFilter(NotificationType.HEALTH) },
                    label = { Text("Sağlık") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2979FF),
                        selectedLabelColor = Color.White
                    )
                )
                FilterChip(
                    selected = selectedFilter?.name == "SECURITY",
                    onClick = { viewModel.updateFilter(NotificationType.SECURITY) },
                    label = { Text("Güvenlik") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2979FF),
                        selectedLabelColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bildirim Listesi
            if (notifications.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Henüz bildirim yok",
                        color = Color(0xFF888888),
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(notifications) { notification ->
                        NotificationCard(notification = notification) {
                            // Bildirim detayına git
                            navController.navigate("notification_detail/${notification.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: Notification,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF141414)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tür İkonu
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            when(notification.type.uppercase()) {
                                "SAĞLIK" -> Color(0xFF4CAF50)
                                "GÜVENLİK" -> Color(0xFFF44336)
                                "ÇEVRE" -> Color(0xFF8BC34A)
                                "KAYIP-BULUNDU" -> Color(0xFFFF9800)
                                "TEKNİK ARIZA" -> Color(0xFF2196F3)
                                else -> Color.Gray
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        when(notification.type.uppercase()) {
                            "SAĞLIK" -> "🏥"
                            "GÜVENLİK" -> "🚨"
                            "ÇEVRE" -> "🌱"
                            "KAYIP-BULUNDU" -> "🔍"
                            "TEKNİK ARIZA" -> "🔧"
                            else -> "❓"
                        },
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Başlık ve Açıklama
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = notification.title,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = notification.description,
                        color = Color(0xFF888888),
                        fontSize = 14.sp,
                        maxLines = 2
                    )
                }

                // Durum Badge
                Box(
                    modifier = Modifier
                        .background(
                            when(notification.status.uppercase()) {
                                "AÇIK" -> Color(0xFFFF9800)
                                "İNCELENİYOR" -> Color(0xFF2196F3)
                                "ÇÖZÜLDÜ" -> Color(0xFF4CAF50)
                                else -> Color.Gray
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = when(notification.status.uppercase()) {
                            "AÇIK" -> "Açık"
                            "İNCELENİYOR" -> "İnceleniyor"
                            "ÇÖZÜLDÜ" -> "Çözüldü"
                            else -> "Bilinmeyen"
                        },
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Zaman
            Text(
                text = notification.createdAt,
                color = Color(0xFF666666),
                fontSize = 12.sp
            )
        }
    }
}