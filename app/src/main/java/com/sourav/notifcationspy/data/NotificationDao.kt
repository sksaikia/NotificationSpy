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

    //TODO add pagination
    @Query("SELECT * FROM notification_data ORDER BY timeStamp DESC LIMIT 50")
    fun getAllNotifs(): List<NotificationData>

    @Query("SELECT * FROM notification_data ORDER BY timeStamp DESC LIMIT 25")
    fun getLatestNotifs(): List<NotificationData>

    @Query("SELECT * FROM notification_data ORDER BY packageName")
    fun getNotificationSortedByPackageName(): List<NotificationData>

    @Query("SELECT * FROM notification_data WHERE packageName = :packageName ORDER BY timeStamp DESC")
    fun getAppNotification(packageName: String): List<NotificationData>

    @Query("SELECT COUNT(id) FROM notification_data WHERE packageName = :packageName")
    fun getAppNotificationCount(packageName: String): Int
}