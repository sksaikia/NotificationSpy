package com.sourav.notifcationspy.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.awt.font.TextAttribute

@Entity(tableName = "notification_data")
data class NotificationData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val packageName: String?,
    val heading: String?,
    val bodyText: String?,
    val timeStamp: Long
)
