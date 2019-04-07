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

@file:JvmName("DrawableUtils")

package com.arthurivanets.commons.ktx

import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt


/**
 *
 */
fun Drawable.applyColor(@ColorInt color : Int, mode : PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP) : Drawable {
    return this.mutate().let {
        it.setColorFilter(color, mode)
        return@let it
    }
}


/**
 *
 */
fun Drawable.inferBounds(canvas : Canvas) {
    this.setBounds(0, 0, canvas.width, canvas.height)
}