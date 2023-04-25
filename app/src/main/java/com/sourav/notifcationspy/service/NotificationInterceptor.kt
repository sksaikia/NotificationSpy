package com.sourav.notifcationspy.service

import android.app.Notification
import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.sourav.notifcationspy.data.NotificationDao
import com.sourav.notifcationspy.data.NotificationData
import com.sourav.notifcationspy.util.extensions.toBlankOrString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationInterceptor: NotificationListenerService() {

    private var context: Context? = null

    @Inject
    lateinit var dao : NotificationDao

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val notificationData = NotificationData(
            0,
            sbn?.packageName?.toBlankOrString(),
            sbn?.notification?.extras?.getString(Notification.EXTRA_TITLE)?.toBlankOrString(),
            sbn?.notification?.extras?.getString(Notification.EXTRA_TEXT)?.toBlankOrString(),
            sbn?.postTime ?: 0
        )

        scope.launch {
            dao.addNotifInfo(notificationData)
        }

        super.onNotificationPosted(sbn)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        context = null
    }

}
