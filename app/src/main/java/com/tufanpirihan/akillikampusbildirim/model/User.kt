package com.tufanpirihan.akillikampusbildirim.model

data class User(
    val id: String? = null,
    val email: String,
    val password: String? = null,
    val fullName: String,
    val department: String,
    val role: UserRole = UserRole.USER
)

enum class UserRole {
    USER, ADMIN
}