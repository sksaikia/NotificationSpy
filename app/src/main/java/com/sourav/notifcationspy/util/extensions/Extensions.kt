package com.sourav.notifcationspy.util.extensions


fun String?.toBlankOrString(): String {
    return this ?: ""
}