package com.sourav.notifcationspy.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.sourav.notifcationspy.R
import com.sourav.notifcationspy.presentation.destinations.AllNotificationsScreenDestination
import com.sourav.notifcationspy.presentation.destinations.AppWiseNotifScreenDestination
import com.sourav.notifcationspy.presentation.destinations.HomeScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home),
    AllNotifications(AllNotificationsScreenDestination, Icons.Default.AccountBox, R.string.app_wise_notifs),
    AppWiseNotifications(AppWiseNotifScreenDestination, Icons.Default.Settings, R.string.all_notifications),
}