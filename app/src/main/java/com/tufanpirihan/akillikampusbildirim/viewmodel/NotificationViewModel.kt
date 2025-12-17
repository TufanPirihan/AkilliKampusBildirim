package com.tufanpirihan.akillikampusbildirim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tufanpirihan.akillikampusbildirim.model.Notification
import com.tufanpirihan.akillikampusbildirim.model.NotificationType
import com.tufanpirihan.akillikampusbildirim.repository.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedFilter = MutableStateFlow<NotificationType?>(null)
    val selectedFilter: StateFlow<NotificationType?> = _selectedFilter.asStateFlow()

    init {
        fetchNotifications()
    }

    fun fetchNotifications() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getReports()
                if (response.isSuccessful) {
                    _notifications.value = response.body() ?: emptyList()
                } else {
                    // Hata yönetimi
                }
            } catch (e: Exception) {
                // Hata yönetimi
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterNotifications()
    }

    fun updateFilter(type: NotificationType?) {
        _selectedFilter.value = type
        filterNotifications()
    }

    private fun filterNotifications() {
        viewModelScope.launch {
            val filteredList = _notifications.value
                .filter { notification ->
                    val matchesQuery = notification.title.contains(_searchQuery.value, ignoreCase = true) ||
                            notification.description.contains(_searchQuery.value, ignoreCase = true)

                    val matchesFilter = _selectedFilter.value == null ||
                            when(_selectedFilter.value) {
                                NotificationType.HEALTH -> notification.type.uppercase() == "SAĞLIK"
                                NotificationType.SECURITY -> notification.type.uppercase() == "GÜVENLİK"
                                NotificationType.ENVIRONMENT -> notification.type.uppercase() == "ÇEVRE"
                                NotificationType.LOST_FOUND -> notification.type.uppercase() == "KAYIP-BULUNDU"
                                NotificationType.TECHNICAL -> notification.type.uppercase() == "TEKNİK ARIZA"
                                null -> true
                            }

                    matchesQuery && matchesFilter
                }

            _notifications.value = filteredList
        }
    }
}