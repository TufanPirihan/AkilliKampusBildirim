package com.tufanpirihan.akillikampusbildirim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                // Geçici bir doğrulama
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Geçersiz giriş bilgileri")
                }
            } catch(e: Exception) {
                _loginState.value = LoginState.Error(e.localizedMessage ?: "Bilinmeyen hata")
            }
        }
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}