package com.sourav.notifcationspy.data.repository

import com.sourav.notifcationspy.data.NotificationDao
import com.sourav.notifcationspy.data.NotificationData
import com.sourav.notifcationspy.domain.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationRepositoryImpl(
    private val dao: NotificationDao
) : NotificationRepository {

    override suspend fun addNotifData(notificationData: NotificationData) {
        dao.addNotifInfo(notificationData)
    }

    override suspend fun getAllNotifData(): Flow<com.sourav.notifcationspy.util.Result<List<NotificationData>>> {
       return flow {
           emit(com.sourav.notifcationspy.util.Result.Loading(isLoading = true))
           val list = dao.getAllNotifs()
           emit(com.sourav.notifcationspy.util.Result.Success(data = list))
           emit(com.sourav.notifcationspy.util.Result.Loading(isLoading = false))
       }
    }
}