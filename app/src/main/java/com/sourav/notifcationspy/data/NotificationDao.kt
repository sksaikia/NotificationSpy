package com.sourav.notifcationspy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotifInfo(notifData: NotificationData)

    @Query("SELECT * FROM notification_data ORDER BY timeStamp DESC")
    fun getAllNotifs(): Flow<List<NotificationData>>

    @Query("SELECT * FROM notification_data ORDER BY timeStamp DESC LIMIT 25")
    fun getLatestNotifs(): Flow<List<NotificationData>>

    @Query("SELECT * FROM notification_data ORDER BY packageName")
    fun getNotificationSortedByPackageName(): Flow<List<NotificationData>>

    @Query("SELECT * FROM notification_data WHERE packageName = :packageName ORDER BY timeStamp DESC")
    fun getAppNotification(packageName: String): Flow<List<NotificationData>>

    @Query("SELECT COUNT(id) FROM notification_data WHERE packageName = :packageName")
    fun getAppNotificationCount(packageName: String): Int
}