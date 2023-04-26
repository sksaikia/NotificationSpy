package com.sourav.notifcationspy.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourav.notifcationspy.domain.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationSpyViewModel @Inject constructor(
    private val repository: NotificationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationSpyUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<NotificationSpyUiAction>(replay = 1)
    val uiAction = _uiAction.asSharedFlow()

    fun getAllNotificationData() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllNotifData().collectLatest { result ->
                when (result) {
                    is com.sourav.notifcationspy.util.Result.Success -> {
                        _uiState.update {
                            it.copy(
                                listOfLatestNotification = result.data,
                            )
                        }
                        Log.d("FATAL", "getAllNotificationData: ${result.data}")
                    }
                    is com.sourav.notifcationspy.util.Result.Loading -> {
                    }
                    is com.sourav.notifcationspy.util.Result.Error -> {
                    }
                }
            }
        }
    }
}
