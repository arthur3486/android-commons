/*
 * Copyright 2018 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("ActivityUtils")

package com.arthurivanets.commons.ktx

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.arthurivanets.commons.SdkInfo
import com.arthurivanets.commons.SdkInfo.IS_AT_LEAST_MARSHMALLOW
import com.arthurivanets.commons.SdkVersions


/**
 *
 */
@SuppressWarnings("NewApi")
fun Activity.setStatusBarColor(@ColorInt color : Int) {
    if(SdkInfo.IS_AT_LEAST_LOLLIPOP) {
        this.window.statusBarColor = color
    }
}


/**
 *  A workaround to be used when you need to set a specific screen orientation to be used by an Activity.
 *  The use of this method instead of the .setRequestedOrientation(...) is mandatory, as the standard one
 *  has major problems which lead to crashes on API v26.
 */
fun Activity.requestScreenOrientation(orientation : Int) {
    if((Build.VERSION.SDK_INT != Build.VERSION_CODES.O)
        || !orientation.isFixedOrientation) {
        requestedOrientation = orientation
    }
}


/**
 *
 */
fun Activity.requestPortraitScreenOrientation() {
    requestScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
}


/**
 *
 */
fun Activity.requestLandscapeScreenOrientation() {
    requestScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
}


/**
 *
 */
fun Activity.requestReverseLandscapeScreenOrientation() {
    requestScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)
}


/**
 *
 */
@RequiresApi(SdkVersions.KITKAT)
fun Activity.requestFullScreenMode() {
    window.decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_FULLSCREEN
        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    )
}


/**
 *
 */
fun Activity.checkPermissions(requestCode : Int, vararg permissions : String) : Boolean {
    return if(IS_AT_LEAST_MARSHMALLOW && !checkSelfPermissionsCompat(*permissions)) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
        false
    } else {
        true
    }
}


/**
 *
 */
@RequiresApi(SdkVersions.KITKAT)
fun Activity.enterImmersiveMode() {
    window.decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_FULLSCREEN
        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    )
}


/**
 *  Allows one to determine whether the current Activity is set to be full screen.
 */
fun Activity.isFullScreen() : Boolean {
    return ((window.attributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0)
}


/**
 *  Enables the "Always-on screen" mode.
 */
fun Activity.enableKeepScreenOn() {
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}


/**
 *  Disables the "Always-on screen" mode.
 */
fun Activity.disableKeepScreenOn() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}