package com.tufanpirihan.akillikampusbildirim.repository

import com.tufanpirihan.akillikampusbildirim.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth Endpoints
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("forgot-password")
    suspend fun forgotPassword(@Body email: String): Response<Unit>

    // Profile Endpoints
    @GET("profile")
    suspend fun getUserProfile(): Response<User>

    @PUT("update-profile")
    suspend fun updateProfile(@Body user: User): Response<User>

    // Report (Notification) Endpoints
    @POST("reports")
    suspend fun createReport(@Body notification: Notification): Response<Notification>

    @GET("get-reports")
    suspend fun getReports(): Response<List<Notification>>

    // Follow Endpoints
    @POST("follow")
    suspend fun followReport(@Body reportId: String): Response<Unit>

    @GET("get-follow")
    suspend fun getFollowedReports(): Response<List<Notification>>
}