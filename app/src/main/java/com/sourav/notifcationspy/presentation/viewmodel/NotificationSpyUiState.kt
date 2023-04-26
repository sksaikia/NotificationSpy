package com.sourav.notifcationspy.presentation.viewmodel

import com.sourav.notifcationspy.data.NotificationData

data class NotificationSpyUiState(
    var listOfLatestNotification: List<NotificationData>? = null,
    var isLoading: Boolean? = null
)