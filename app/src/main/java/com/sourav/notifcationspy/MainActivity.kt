package com.sourav.notifcationspy

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.sourav.notifcationspy.presentation.BottomBarDestination
import com.sourav.notifcationspy.presentation.NavGraphs
import com.sourav.notifcationspy.presentation.appCurrentDestinationAsState
import com.sourav.notifcationspy.presentation.destinations.Destination
import com.sourav.notifcationspy.presentation.startAppDestination
import com.sourav.notifcationspy.service.NotificationInterceptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme

            val navController = rememberNavController()
            Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
            }
            if (!isNotificationServiceEnabled()) {
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
            }
            startService(Intent(applicationContext, NotificationInterceptor::class.java))
        }
    }

    private fun isNotificationServiceEnabled(): Boolean {
        val pkgName = packageName
        val listeners: String = Settings.Secure.getString(
            contentResolver,
            ENABLED_NOTIFICATION_LISTENERS,
        )

        if (!TextUtils.isEmpty(listeners)) {
            val names = listeners.split(":".toRegex()).dropLastWhile {
                it.isEmpty()
            }.toTypedArray()

            for (i in names.indices) {
                val component = ComponentName.unflattenFromString(names[i])
                if (component != null) {
                    if (TextUtils.equals(pkgName, component.packageName)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}

@Composable
fun BottomBar(
    navController: NavController,
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    // if you are using material 2 then you  should use  bottom navigation bar
    NavigationBar {
        BottomBarDestination.values().forEach { destination ->
            // similarly with material 2  use bottom nav item
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        val navigationRoutes = BottomBarDestination.values()

                        val firstBottomBarDestination = navController.currentBackStack.value
                            .firstOrNull { navBackStackEntry -> checkForDestinations(navigationRoutes, navBackStackEntry) }
                            ?.destination
                        // remove all navigation items from the stack
                        // so only the currently selected screen remains in the stack
                        if (firstBottomBarDestination != null) {
                            popUpTo(firstBottomBarDestination.id) {
                                inclusive = true
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(destination.label),
                    )
                },
                label = { Text(text = stringResource(destination.label)) },
            )
        }
    }
}

fun checkForDestinations(
    navigationRoutes: Array<BottomBarDestination>,
    navBackStackEntry: NavBackStackEntry,
): Boolean {
    navigationRoutes.forEach {
        if (it.direction.route == navBackStackEntry.destination.route) {
            return true
        }
    }
    return false
}
