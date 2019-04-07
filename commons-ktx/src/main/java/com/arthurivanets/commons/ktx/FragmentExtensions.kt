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

@file:JvmName("FragmentUtils")

package com.arthurivanets.commons.ktx

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Environment
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.arthurivanets.commons.SdkInfo.IS_AT_LEAST_MARSHMALLOW
import java.io.File
import java.io.FileNotFoundException


/**
 *
 */
val Fragment.appPicturesDirectory : File
    get() {
        assertAttachedToContext()
        return (context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: throw FileNotFoundException("App Pictures Directory Not Found."))
    }


/**
 *
 */
fun Fragment.assertAttachedToActivity() {
    if(activity == null) {
        throw IllegalStateException("The Fragment is not attached to the Activity.")
    }
}


/**
 *
 */
fun Fragment.assertAttachedToContext() {
    if(context == null) {
        throw IllegalStateException("The Fragment is not attached to the Context.")
    }
}


/**
 *
 */
fun Fragment.checkPermissions(requestCode : Int, vararg permissions : String) : Boolean {
    return if(IS_AT_LEAST_MARSHMALLOW && !checkSelfPermissionsCompat(*permissions)) {
        requestPermissions(permissions, requestCode)
        false
    } else {
        true
    }
}


/**
 *
 */
fun Fragment.checkSelfPermissionsCompat(vararg permissions : String) : Boolean {
    assertAttachedToContext()
    return context!!.checkSelfPermissionsCompat(*permissions)
}


/**
 *
 */
fun Fragment.checkSelfPermissionCompat(permission : String) : Boolean {
    assertAttachedToContext()
    return context!!.checkSelfPermissionCompat(permission)
}


/**
 *
 */
fun Fragment.getColorCompat(@ColorRes colorResourceId : Int) : Int {
    return (this.context?.getColorCompat(colorResourceId) ?: Color.TRANSPARENT)
}


/**
 *
 */
fun Fragment.getDrawable(@DrawableRes drawableResourceId : Int) : Drawable? {
    return ContextCompat.getDrawable(this.context!!, drawableResourceId)
}


/**
 *
 */
fun Fragment.getColoredDrawable(@DrawableRes drawableResourceId : Int, @ColorInt color : Int) : Drawable? {
    return this.getDrawable(drawableResourceId)?.applyColor(color)
}


/**
 *
 */
fun Fragment.getDimension(@DimenRes dimensionResourceId : Int) : Float {
    return this.resources.getDimension(dimensionResourceId)
}


/**
 *
 */
fun Fragment.getDimensionPixelSize(@DimenRes dimensionResourceId : Int) : Int {
    return this.resources.getDimensionPixelSize(dimensionResourceId)
}


/**
 *
 */
fun Fragment.shortToast(message : String) {
    toast(message, Toast.LENGTH_SHORT)
}


/**
 *
 */
fun Fragment.longToast(message : String) {
    toast(message, Toast.LENGTH_LONG)
}


/**
 *
 */
fun Fragment.toast(message : String, duration : Int = Toast.LENGTH_SHORT) {
    context?.toast(message, duration)
}