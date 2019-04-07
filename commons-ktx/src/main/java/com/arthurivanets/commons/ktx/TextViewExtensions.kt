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

@file:JvmName("TextViewUtils")

package com.arthurivanets.commons.ktx

import android.graphics.Typeface
import android.text.InputType
import android.text.Spannable
import android.util.TypedValue
import android.widget.TextView


/**
 *
 */
fun TextView.setRawTextSize(rawTextSize : Float) {
    this.setTextSize(TypedValue.COMPLEX_UNIT_PX, rawTextSize)
}


/**
 *
 */
fun TextView.setFontFamily(fontFamily : String) {
    this.typeface = Typeface.create(fontFamily, Typeface.NORMAL)
}


/**
 *
 */
fun TextView.asSingleLine() {
    this.setLines(1)
    this.minLines = 1
    this.maxLines = 1
}


/**
 *
 */
fun TextView.holdsRawText() {
    this.inputType = InputType.TYPE_CLASS_TEXT
}


/**
 *
 */
fun TextView.holdsEmailAddress() {
    this.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
}


/**
 *
 */
fun TextView.holdsPersonName() {
    this.inputType = (android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
}


/**
 *
 */
fun TextView.holdsVisiblePassword() {
    this.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
}


/**
 *
 */
fun TextView.holdsPassword() {
    this.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
}


/**
 *
 */
fun TextView.isHoldingVisiblePassword() : Boolean {
    return this.inputType.containsBits(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
}


/**
 *
 */
fun TextView.isHoldingPassword() : Boolean {
    return with(this.inputType) {
        containsBits(InputType.TYPE_NUMBER_VARIATION_PASSWORD)
        || containsBits(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD)
        || containsBits(InputType.TYPE_TEXT_VARIATION_PASSWORD)
        || containsBits(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
    }
}


/**
 *
 */
fun TextView.goneIfEmpty() {
    this.setVisible(!this.text.isEmpty())
}


/**
 *
 */
inline fun TextView.setSpannableFactory(crossinline block : (CharSequence?) -> Spannable) {
    this.setSpannableFactory(spannableFactory(block))
}


/**
 *
 */
inline fun spannableFactory(crossinline block : (CharSequence?) -> Spannable) : Spannable.Factory {
    return object : Spannable.Factory() {
        override fun newSpannable(source : CharSequence?) : Spannable {
            return block(source)
        }
    }
}