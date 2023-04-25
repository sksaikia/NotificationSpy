package com.sourav.notifcationspy.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NotificationData::class],
    version = 1
)
abstract class NotificationDatabase: RoomDatabase(){
    abstract val dao: NotificationDao

    companion object {
        const val DATABASE_NAME = "notification_data_db"
    }
}