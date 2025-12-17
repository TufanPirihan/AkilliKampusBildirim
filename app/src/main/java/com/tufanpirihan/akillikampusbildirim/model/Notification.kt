package com.tufanpirihan.akillikampusbildirim.model

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("ID") val id: String,
    @SerializedName("UserID") val userId: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Description") val description: String,
    @SerializedName("Type") val type: String, // String olarak değiştirildi
    @SerializedName("Status") val status: String, // String olarak değiştirildi
    @SerializedName("Location") val location: Location,
    @SerializedName("CreatedAt") val createdAt: String
)

enum class NotificationType {
    HEALTH, SECURITY, ENVIRONMENT, LOST_FOUND, TECHNICAL
}

enum class NotificationStatus {
    OPEN, IN_PROGRESS, RESOLVED
}

data class Location(
    @SerializedName("Lat") val latitude: Double,
    @SerializedName("Lng") val longitude: Double
)