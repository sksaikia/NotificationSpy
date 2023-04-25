package com.sourav.notifcationspy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.content.Intent
import com.sourav.notifcationspy.service.NotificationInterceptor

@HiltAndroidApp
class NotificationSpyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startService(Intent(applicationContext, NotificationInterceptor::class.java))
    }

}