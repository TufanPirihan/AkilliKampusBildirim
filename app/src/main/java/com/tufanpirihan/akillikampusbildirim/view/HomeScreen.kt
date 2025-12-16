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
import androidx.navigation.NavHostController
import com.tufanpirihan.akillikampusbildirim.model.Notification
import com.tufanpirihan.akillikampusbildirim.model.NotificationStatus
import com.tufanpirihan.akillikampusbildirim.model.NotificationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf<NotificationType?>(null) }

    // Örnek bildirim listesi (gerçekte backend'den gelecek)
    // Örnek bildirim listesi (gerçekte backend'den gelecek)
    val notifications = remember {
        listOf(
            Notification(
                id = "1",
                title = "Kütüphane Acil Durum",
                description = "Kütüphanede yangın alarmı çaldı",
                type = NotificationType.SECURITY,
                status = NotificationStatus.IN_PROGRESS,
                location = com.tufanpirihan.akillikampusbildirim.model.Location(
                    latitude = 40.0,
                    longitude = 32.0,
                    address = "Merkez Kütüphane"
                ),
                createdAt = "2 saat önce",
                userId = "user1"
            ),
            Notification(
                id = "2",
                title = "İlk Yardım İhtiyacı",
                description = "Kafeteryada bir öğrencinin ilk yardıma ihtiyacı var",
                type = NotificationType.HEALTH,
                status = NotificationStatus.OPEN,
                location = com.tufanpirihan.akillikampusbildirim.model.Location(
                    latitude = 40.0,
                    longitude = 32.0,
                    address = "Öğrenci Kafeteryası"
                ),
                createdAt = "30 dakika önce",
                userId = "user2"
            ),
            Notification(
                id = "3",
                title = "Kayıp Cüzdan",
                description = "Mavi renkli cüzdan kaybedildi",
                type = NotificationType.LOST_FOUND,
                status = NotificationStatus.RESOLVED,
                location = com.tufanpirihan.akillikampusbildirim.model.Location(
                    latitude = 40.0,
                    longitude = 32.0,
                    address = "A Blok"
                ),
                createdAt = "1 gün önce",
                userId = "user3"
            )
        )
    }

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
                onClick = { /* Yeni bildirim oluştur */ },
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
                onValueChange = { searchQuery = it },
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
                    onClick = { selectedFilter = null },
                    label = { Text("Tümü") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2979FF),
                        selectedLabelColor = Color.White
                    )
                )
                FilterChip(
                    selected = selectedFilter == NotificationType.HEALTH,
                    onClick = { selectedFilter = NotificationType.HEALTH },
                    label = { Text("Sağlık") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2979FF),
                        selectedLabelColor = Color.White
                    )
                )
                FilterChip(
                    selected = selectedFilter == NotificationType.SECURITY,
                    onClick = { selectedFilter = NotificationType.SECURITY },
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
                            when(notification.type) {
                                NotificationType.HEALTH -> Color(0xFF4CAF50)
                                NotificationType.SECURITY -> Color(0xFFF44336)
                                NotificationType.ENVIRONMENT -> Color(0xFF8BC34A)
                                NotificationType.LOST_FOUND -> Color(0xFFFF9800)
                                NotificationType.TECHNICAL -> Color(0xFF2196F3)
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        when(notification.type) {
                            NotificationType.HEALTH -> "🏥"
                            NotificationType.SECURITY -> "🚨"
                            NotificationType.ENVIRONMENT -> "🌱"
                            NotificationType.LOST_FOUND -> "🔍"
                            NotificationType.TECHNICAL -> "🔧"
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
                            when(notification.status) {
                                NotificationStatus.OPEN -> Color(0xFFFF9800)
                                NotificationStatus.IN_PROGRESS -> Color(0xFF2196F3)
                                NotificationStatus.RESOLVED -> Color(0xFF4CAF50)
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = when(notification.status) {
                            NotificationStatus.OPEN -> "Açık"
                            NotificationStatus.IN_PROGRESS -> "İnceleniyor"
                            NotificationStatus.RESOLVED -> "Çözüldü"
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