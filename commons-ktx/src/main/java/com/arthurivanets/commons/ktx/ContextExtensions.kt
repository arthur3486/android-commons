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

@file:JvmName("ContextUtils")

package com.arthurivanets.commons.ktx

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.arthurivanets.commons.SdkInfo.IS_AT_LEAST_NOUGAT
import com.arthurivanets.commons.SdkVersions
import java.io.File
import java.io.FileNotFoundException
import java.util.*


/**
 *
 */
@get:JvmName("getLocale")
val Context.locale : Locale
    @SuppressWarnings("NewApi") get() {
        return if(IS_AT_LEAST_NOUGAT) {
            this.resources.configuration.locales[0]
        } else {
            this.resources.configuration.locale
        }
    }

/**
 *
 */
@get:JvmName("getAppPicturesDirectory")
val Context.appPicturesDirectory : File
    get() = (getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: throw FileNotFoundException("App Pictures Directory Not Found."))

/**
 *
 */
@get:JvmName("getAppMoviesDirectory")
val Context.appMoviesDirectory : File
    get() = (getExternalFilesDir(Environment.DIRECTORY_MOVIES) ?: throw FileNotFoundException("App Movies Directory Not Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.KITKAT)
@get:JvmName("getAppDocumentsDirectory")
val Context.appDocumentsDirectory : File
    get() = (getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: throw FileNotFoundException("App Documents Directory Not Found."))

/**
 *
 */
@get:JvmName("getStatusBarSize")
val Context.statusBarSize : Int
    get() = this.resources.let {
        it.getDimensionPixelSize(it.getIdentifier(
            "status_bar_height",
            "dimen",
            "android"
        ))
    }

/**
 *
 */
@get:JvmName("getNavigationBarSize")
val Context.navigationBarSize : Int
    get() = this.resources.let {
        it.getDimensionPixelSize(it.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        ))
    }


/**
 *
 */
fun Context.newLayoutInflater() : LayoutInflater {
    return LayoutInflater.from(this)
}


/**
 *
 */
fun Context.inflateView(@LayoutRes layoutResourceId : Int,
                        root : ViewGroup?,
                        attachToRoot : Boolean = true) : View {
    return newLayoutInflater().inflate(
        layoutResourceId,
        root,
        attachToRoot
    )
}


/**
 *
 */
fun Context.showKeyboard(view : View) {
    this.inputMethodManager.showSoftInput(view, 0)
}


/**
 *
 */
fun Context.hideKeyboard(view : View) {
    this.inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


/**
 *
 */
fun Context.getColorCompat(@ColorRes colorResourceId : Int) : Int {
    return ContextCompat.getColor(this, colorResourceId)
}


/**
 *
 */
fun Context.getDrawableCompat(@DrawableRes drawableResourceId : Int) : Drawable? {
    return ContextCompat.getDrawable(this, drawableResourceId)
}


/**
 *
 */
fun Context.getColoredDrawable(@DrawableRes drawableResourceId : Int, @ColorInt color : Int) : Drawable? {
    return this.getDrawableCompat(drawableResourceId)?.applyColor(color)
}


/**
 *
 */
fun Context.getDimension(@DimenRes dimensionResourceId : Int) : Float {
    return this.resources.getDimension(dimensionResourceId)
}


/**
 *
 */
fun Context.getDimensionPixelSize(@DimenRes dimensionResourceId : Int) : Int {
    return this.resources.getDimensionPixelSize(dimensionResourceId)
}


/**
 *
 */
fun Context.checkSelfPermissionsCompat(vararg permissions : String) : Boolean {
    if(permissions.isEmpty()) {
        return false
    }

    var allGranted = false

    for(permission in permissions) {
        allGranted = checkSelfPermissionCompat(permission)

        if(!allGranted) {
            break
        }
    }

    return allGranted
}


/**
 *
 */
fun Context.checkSelfPermissionCompat(permission : String) : Boolean {
    return (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
}


/**
 *
 */
fun isPermissionSetGranted(grantResults : IntArray) : Boolean {
    for(grantResult in grantResults) {
        if(grantResult != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }

    return true
}


/**
 *
 */
fun Context.extractStyledAttributes(attributes : AttributeSet,
                                    attrs : IntArray,
                                    block : TypedArray.() -> Unit) {
    val typedArray = obtainStyledAttributes(attributes, attrs, 0, 0)

    try {
        block(typedArray)
    } finally {
        typedArray?.recycle()
    }
}


/**
 *
 */
fun Context.shortToast(message : String) {
    toast(message, Toast.LENGTH_SHORT)
}


/**
 *
 */
fun Context.longToast(message : String) {
    toast(message, Toast.LENGTH_LONG)
}


/**
 *
 */
fun Context.toast(message : String, duration : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}