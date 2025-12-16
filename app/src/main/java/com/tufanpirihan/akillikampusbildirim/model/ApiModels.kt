package com.tufanpirihan.akillikampusbildirim.model

// Login Request
data class LoginRequest(
    val email: String,
    val password: String
)

// Login Response
data class LoginResponse(
    val token: String,
    val user: User
)

// Register Request
data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val department: String
)

// Register Response
data class RegisterResponse(
    val message: String,
    val user: User
)

// API Response Wrapper
data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?
)