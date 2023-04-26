package com.sourav.notifcationspy.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.sourav.notifcationspy.presentation.composition.NotificationDataCard
import com.sourav.notifcationspy.presentation.viewmodel.NotificationSpyViewModel
import com.sourav.notifcationspy.util.ViewModelHelper.activityViewModel

@Composable
@Destination(start = true)
fun HomeScreen(viewModel: NotificationSpyViewModel = activityViewModel()) {
    viewModel.getAllNotificationData()

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        itemsIndexed(items = uiState.listOfNotification ?: emptyList()) { index, data ->
            NotificationDataCard(notificationData = data)
        }
    }
}
