package com.sourav.notifcationspy.util.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

fun String?.toBlankOrString(): String {
    return this ?: ""
}

fun String.getDisplayNameFromPackageName(context: Context): String {
    try {
        val info = context.packageManager.getApplicationInfo(this, PackageManager.GET_META_DATA)
        val appName = context.packageManager.getApplicationLabel(info)
        return appName.toString()
    } catch (e: PackageManager.NameNotFoundException) {
        return "Uninstalled App"
    }
}

fun getImageFromPackageName(context: Context, packageName: String): Drawable {
    return context.packageManager.getApplicationIcon(packageName)
    //   this.setImageDrawable(icon)
}
