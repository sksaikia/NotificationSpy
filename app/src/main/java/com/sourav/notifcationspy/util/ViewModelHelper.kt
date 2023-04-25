package com.sourav.notifcationspy.util

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


object ViewModelHelper {

    @Composable
    inline fun <reified T : ViewModel> activityViewModel(): T {
        return viewModel(LocalContext.current as ComponentActivity)
    }
}
