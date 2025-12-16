package com.tufanpirihan.akillikampusbildirim.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun RegisterScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF050505)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFF141414))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Alanı
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2979FF), Color.Black)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("🎓", color = Color.White, fontSize = 40.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Kayıt Ol",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Ad Soyad Input
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Ad Soyad", color = Color(0xFF888888)) },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF555555), shape = CircleShape)
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

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("E-posta", color = Color(0xFF888888)) },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF555555), shape = CircleShape)
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

            // Birim Input
            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Birim", color = Color(0xFF888888)) },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF555555), shape = CircleShape)
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

            // Şifre Input
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Şifre", color = Color(0xFF888888)) },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF555555), shape = CircleShape)
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

            // Şifre Tekrar Input
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1F1F), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Şifre Tekrar", color = Color(0xFF888888)) },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF555555), shape = CircleShape)
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

            Spacer(modifier = Modifier.height(24.dp))

            // Kayıt Ol Butonu
            Button(
                onClick = {
                    // Kayıt işlemi
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2979FF)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "KAYIT OL",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Giriş Yap Linki
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Zaten hesabın var mı? ",
                    color = Color(0xFF888888),
                    fontSize = 14.sp
                )
                Text(
                    text = "Giriş Yap",
                    color = Color(0xFF2979FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }
        }
    }
}