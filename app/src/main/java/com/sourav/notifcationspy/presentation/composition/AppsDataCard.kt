package com.sourav.notifcationspy.presentation.composition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design.PopText
import com.sourav.notifcationspy.domain.AppsData
import com.sourav.notifcationspy.util.extensions.getDisplayNameFromPackageName
import com.sourav.notifcationspy.util.extensions.getImageFromPackageName
import com.sourav.notifcationspy.util.extensions.toBlankOrString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsDataCard(
    notificationData: AppsData,
    modifier: Modifier = Modifier
) {
    //   val notificationData = NotificationData(1, "ABC", "asddas", "asdas", 1234)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(80.dp).background(Color.White).padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getImageFromPackageName(LocalContext.current, notificationData.packageName ?: ""))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.width(50.dp).height(50.dp).padding(start = 8.dp),
            )

            notificationData.packageName.toBlankOrString().let {
                PopText(
                    text = it.getDisplayNameFromPackageName(LocalContext.current),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
    }
}
