package com.sourav.notifcationspy.presentation.home

import androidx.compose.runtime.Composable
import com.example.design.PopText
import com.ramcosta.composedestinations.annotation.Destination
import com.sourav.notifcationspy.presentation.viewmodel.NotificationSpyViewModel
import com.sourav.notifcationspy.util.ViewModelHelper.activityViewModel

@Composable
@Destination(start = true)
fun HomeScreen(viewModel: NotificationSpyViewModel = activityViewModel()) {
    viewModel.getAllNotificationData()
}
