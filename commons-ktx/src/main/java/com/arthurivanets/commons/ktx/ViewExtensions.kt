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

@file:JvmName("ViewUtils")

package com.arthurivanets.commons.ktx

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.arthurivanets.commons.SdkVersions


/**
 *
 */
fun View.inflateView(@LayoutRes layoutResourceId : Int,
                     root : ViewGroup?,
                     attachToRoot : Boolean = true) : View {
    return context.inflateView(
        layoutResourceId = layoutResourceId,
        root = root,
        attachToRoot = attachToRoot
    )
}


/**
 *
 */
fun View.useNoLayer(paint : Paint? = null) {
    setLayerType(View.LAYER_TYPE_NONE, paint)
}


/**
 *
 */
fun View.useSoftwareLayer(paint : Paint? = null) {
    setLayerType(View.LAYER_TYPE_SOFTWARE, paint)
}


/**
 *
 */
fun View.useHardwareLayer(paint : Paint? = null) {
    setLayerType(View.LAYER_TYPE_HARDWARE, paint)
}


/**
 *
 */
inline fun <T : ViewGroup.LayoutParams> View.updateLayoutParams(crossinline block : T.() -> Unit) {
    val currentLayoutParams = this.layoutParams

    currentLayoutParams?.let { block(it as T) }

    this.layoutParams = currentLayoutParams
}


/**
 *
 */
fun View.updatePadding(paddingLeft : Int = this.paddingLeft,
                       paddingTop : Int = this.paddingTop,
                       paddingRight : Int = this.paddingRight,
                       paddingBottom : Int = this.paddingBottom) {
    this.setPadding(
        paddingLeft,
        paddingTop,
        paddingRight,
        paddingBottom
    )
}


/**
 *
 */
fun View.showKeyboard(requestFocus : Boolean = true) {
    if(requestFocus) {
        requestFocus()
    }

    this.context.showKeyboard(this)
}


/**
 *
 */
fun View.hideKeyboard(clearFocus : Boolean = true) {
    if(clearFocus) {
        clearFocus()
    }

    this.context.hideKeyboard(this)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintEmailAddress() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_EMAIL_ADDRESS)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintName() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_NAME)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintUsername() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_USERNAME)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintPassword() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_PASSWORD)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintPhone() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_PHONE)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintPostalAddress() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_POSTAL_ADDRESS)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintPostalCode() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_POSTAL_CODE)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardNumber() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_NUMBER)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardSecurityCode() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardExpirationDate() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardExpirationMonth() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardExpirationYear() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintCreditCardExpirationDay() = minSdk(SdkVersions.O) {
    setAutofillHintsCompat(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY)
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.addAutofillHints(vararg hints : String) = minSdk(SdkVersions.O) {
    setAutofillHints(*(autofillHints?.toMutableList()?.append(hints) ?: hints.toMutableList()).toTypedArray())
}


/**
 *
 */
@SuppressWarnings("NewApi")
fun View.setAutofillHintsCompat(vararg hints : String) = minSdk(SdkVersions.O) {
    setAutofillHints(*hints)
}


/**
 *
 */
fun View.getDrawable(@DrawableRes drawableResourceId : Int) : Drawable? {
    return ContextCompat.getDrawable(this.context, drawableResourceId)
}


/**
 *
 */
fun View.getColoredDrawable(@DrawableRes drawableResourceId : Int, @ColorInt color : Int) : Drawable? {
    return this.getDrawable(drawableResourceId)?.applyColor(color)
}


/**
 *
 */
fun View.getString(@StringRes stringResourcesId : Int) : String {
    return this.context.getString(stringResourcesId)
}


/**
 *
 */
fun View.getString(@StringRes stringResourcesId : Int, vararg formatArgs : Any) : String {
    return this.context.getString(stringResourcesId, *formatArgs)
}


/**
 *
 */
fun View.getDimension(@DimenRes dimensionResourceId : Int) : Float {
    return this.context.getDimension(dimensionResourceId)
}


/**
 *
 */
fun View.getDimensionPixelSize(@DimenRes dimensionResourceId : Int) : Int {
    return this.context.getDimensionPixelSize(dimensionResourceId)
}


/**
 *
 */
fun View.getColor(@ColorRes colorResourceId : Int) : Int {
    return this.context.getColorCompat(colorResourceId)
}


/**
 *
 */
fun <T> View.getTagAs() : T? {
    return (this.tag as T?)
}


/**
 *
 */
fun View.colorBackground(@ColorInt color : Int, mode : PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP) {
    this.background?.applyColor(color, mode)?.let {
        background = it
    }
}


/**
 *
 */
fun View.cancelActiveAnimations() {
    clearAnimation()
    animate().cancel()
}


/**
 *
 */
fun View.enable() {
    isEnabled = true
}


/**
 *
 */
fun View.disable() {
    isEnabled = false
}


/**
 *
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}


/**
 *
 */
fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}


/**
 *
 */
fun View.makeGone() {
    this.visibility = View.GONE
}


/**
 * @isVisible sets the View visibility to [View.VISIBLE] if true, [View.GONE] if false.
 */
fun View.setVisible(isVisible : Boolean) {
    this.visibility = (if(isVisible) View.VISIBLE else View.GONE)
}


/**
 *
 */
fun View.setMinAlpha() {
    this.alpha = 0f
}


/**
 *
 */
fun View.setMaxAlpha() {
    this.alpha = 1f
}


/**
 *
 */
fun View.setMinScale() {
    setScale(0f)
}


/**
 *
 */
fun View.setMaxScale() {
    setScale(1f)
}


/**
 *
 */
fun View.setScale(scale : Float) {
    this.scaleX = scale
    this.scaleY = scale
}


/**
 *
 */
fun View.isVisible() : Boolean {
    return (this.visibility == View.VISIBLE)
}


/**
 *
 */
fun View.isInvisible() : Boolean {
    return (this.visibility == View.INVISIBLE)
}


/**
 *
 */
fun View.isGone() : Boolean {
    return (this.visibility == View.GONE)
}