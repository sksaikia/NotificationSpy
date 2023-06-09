package com.sourav.notifcationspy.presentation.appwise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.sourav.notifcationspy.presentation.composition.NotificationDataCard
import com.sourav.notifcationspy.presentation.viewmodel.NotificationSpyViewModel
import com.sourav.notifcationspy.util.ViewModelHelper

@Composable
@Destination
fun AppNotificationListScreen(packageName: String,  viewModel: NotificationSpyViewModel = ViewModelHelper.activityViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.getNotificationsByAppName(packageName)
    }

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxHeight(0.9f).background(Color.White)) {
        itemsIndexed(items = uiState.listOfNotificationByPackage ?: emptyList()) { index, data ->
            NotificationDataCard(notificationData = data)
        }
    }
}