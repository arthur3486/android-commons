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

@file:JvmName("TypeUtils")

package com.arthurivanets.commons.ktx

import android.content.Context
import android.util.TypedValue


/**
 *
 */
fun Float.toPx(context : Context) : Float {
    return (this / context.resources.displayMetrics.density)
}


/**
 *
 */
fun Float.toSp(context : Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics)
}


/**
 *
 */
fun Float.toDp(context : Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
}


/**
 *
 */
fun Int.containsBits(bits : Int) : Boolean {
    return ((this and bits) == bits)
}


/**
 *
 */
fun Long.containsBits(bits : Long) : Boolean {
    return ((this and bits) == bits)
}


/**
 *
 */
fun Boolean.asInt() : Int {
    return (if(this) 1 else 0)
}


/**
 *
 */
fun Int.asBoolean() : Boolean {
    return (this == 1)
}


/**
 *
 */
fun Any?.asIntOrDefault(defaultValue : Int) : Int {
    return (if(this is Int) this else defaultValue)
}


/**
 *
 */
fun Any?.asStringOrDefault(defaultValue : String) : String {
    return (if(this is String) this else defaultValue)
}