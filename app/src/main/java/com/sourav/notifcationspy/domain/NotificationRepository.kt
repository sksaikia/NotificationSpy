package com.sourav.notifcationspy.domain

import com.sourav.notifcationspy.data.NotificationData
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun addNotifData(notificationData: NotificationData)

    suspend fun getAllNotifData(): Flow<com.sourav.notifcationspy.util.Result<List<NotificationData>>>

    suspend fun getAppNames(): Flow<com.sourav.notifcationspy.util.Result<List<NotificationData>>>
}