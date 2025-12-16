package com.tufanpirihan.akillikampusbildirim.model

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val type: NotificationType,
    val status: NotificationStatus,
    val location: Location,
    val createdAt: String,
    val userId: String,
    val photos: List<String>? = null
)

enum class NotificationType {
    HEALTH,        // Sağlık
    SECURITY,      // Güvenlik
    ENVIRONMENT,   // Çevre
    LOST_FOUND,    // Kayıp-Buluntu
    TECHNICAL      // Teknik Arıza
}

enum class NotificationStatus {
    OPEN,          // Açık
    IN_PROGRESS,   // İnceleniyor
    RESOLVED       // Çözüldü
}

data class Location(
    val latitude: Double,
    val longitude: Double,
    val address: String? = null
)