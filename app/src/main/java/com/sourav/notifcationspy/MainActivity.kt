package com.sourav.notifcationspy

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ContentInfoCompat.Flags
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.NavGraph
import com.sourav.notifcationspy.presentation.NavGraphs
import com.sourav.notifcationspy.service.NotificationInterceptor
import com.sourav.notifcationspy.ui.theme.NotifcationSpyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifcationSpyTheme {
                // A surface container using the 'background' color from the theme
                DestinationsNavHost(navGraph = NavGraphs.root)

                if(!isNotificationServiceEnabled()) {
                    startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS,))
                }
                startService(Intent(applicationContext, NotificationInterceptor::class.java))
            }
        }
    }

    private fun isNotificationServiceEnabled(): Boolean {
        val pkgName = packageName
        val listeners: String = Settings.Secure.getString(
            contentResolver,
            ENABLED_NOTIFICATION_LISTENERS
        )

        if (!TextUtils.isEmpty(listeners)) {
            val names = listeners.split(":".toRegex()).dropLastWhile {
                it.isEmpty()
            }.toTypedArray()

            for (i in names.indices) {
                val component = ComponentName.unflattenFromString(names[i])
                if (component!=null) {
                    if (TextUtils.equals(pkgName, component.packageName)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}