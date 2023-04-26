package com.sourav.notifcationspy.presentation.composition

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design.PopText
import com.sourav.notifcationspy.R
import com.sourav.notifcationspy.data.NotificationData
import com.sourav.notifcationspy.util.extensions.getDisplayNameFromPackageName
import com.sourav.notifcationspy.util.extensions.getImageFromPackageName
import com.sourav.notifcationspy.util.extensions.toBlankOrString

@Composable
fun NotificationDataCard(
    notificationData: NotificationData,
) {
    //   val notificationData = NotificationData(1, "ABC", "asddas", "asdas", 1234)
    Card(modifier = Modifier.fillMaxWidth().background(Color.White)) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(12.dp).background(Color.White)) {
            val (icon, appName, headerText, bodyText, timeStamp) = createRefs()


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getImageFromPackageName(LocalContext.current, notificationData.packageName ?: ""))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.width(60.dp).height(60.dp).padding(start = 8.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            notificationData.packageName?.toBlankOrString()?.let {
                PopText(
                    text = it.getDisplayNameFromPackageName(LocalContext.current),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp).constrainAs(appName) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                    },
                )
            }
            notificationData.heading?.toBlankOrString()?.let {
                PopText(
                    text = it,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 16.dp).constrainAs(headerText) {
                        top.linkTo(appName.bottom)
                        start.linkTo(icon.end)
                    },
                )
            }

            notificationData.bodyText?.toBlankOrString()?.let {
                PopText(
                    text = it,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(start = 16.dp).constrainAs(bodyText) {
                        top.linkTo(icon.bottom)
                        start.linkTo(parent.start)
                    },
                )
            }

            notificationData.timeStamp?.let {
                PopText(
                    text = it.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(start = 16.dp).constrainAs(timeStamp) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                )
            }
//
//            notificationData.apply {
//                this.heading?.toBlankOrString()?.let { PopText(text = it) }
//                this.packageName?.toBlankOrString()?.let { PopText(text = it) }
//            }
        }
    }
}
