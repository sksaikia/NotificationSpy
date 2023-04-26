package com.sourav.notifcationspy.presentation.appwise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.sourav.notifcationspy.domain.AppsData
import com.sourav.notifcationspy.presentation.composition.AppsDataCard
import com.sourav.notifcationspy.presentation.viewmodel.NotificationSpyViewModel
import com.sourav.notifcationspy.util.ViewModelHelper
import com.sourav.notifcationspy.util.extensions.getDisplayNameFromPackageName

@Composable
@Destination
fun AppWiseNotifScreen(viewModel: NotificationSpyViewModel = ViewModelHelper.activityViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.getAppNames()
    }

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val listOfApps = uiState.listOfApps?.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER) {
            (it.packageName ?: "").getDisplayNameFromPackageName(context)
        },
    )
    val mutableSet = mutableMapOf<String, AppsData>()
    listOfApps?.forEach {
        mutableSet[it.packageName ?: ""] = AppsData(it.packageName ?: "", it.packageName ?: "")
    }

    val finalList = mutableSet.values.toList()

    LazyColumn(modifier = Modifier.fillMaxHeight(0.9f).background(Color.White)) {
        itemsIndexed(items = finalList) { _, data ->
            AppsDataCard(notificationData = data)
        }
    }
}
