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

@file:JvmName("OrientationUtils")

package com.arthurivanets.commons.ktx

import android.content.pm.ActivityInfo.*


/**
 * Returns true if the specified orientation is considered fixed.
 * @hide
 */
@get:JvmName("isFixedOrientation")
val Int.isFixedOrientation : Boolean
    get() {
        return (
            this.isFixedOrientationLandscape
            || this.isFixedOrientationPortrait
            || (this == SCREEN_ORIENTATION_LOCKED)
        )
    }

/**
 * Returns true if the activity's orientation is fixed to landscape.
 * @hide
 */
@get:JvmName("isFixedOrientationLandscape")
val Int.isFixedOrientationLandscape : Boolean
    get() {
        return (
            (this == SCREEN_ORIENTATION_LANDSCAPE)
            || (this == SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            || (this == SCREEN_ORIENTATION_REVERSE_LANDSCAPE)
            || (this == SCREEN_ORIENTATION_USER_LANDSCAPE)
        )
    }

/**
 * Returns true if the activity's orientation is fixed to portrait.
 * @hide
 */
@get:JvmName("isFixedOrientationPortrait")
val Int.isFixedOrientationPortrait : Boolean
    get() {
        return (
            (this == SCREEN_ORIENTATION_PORTRAIT)
            || (this == SCREEN_ORIENTATION_SENSOR_PORTRAIT)
            || (this == SCREEN_ORIENTATION_REVERSE_PORTRAIT)
            || (this == SCREEN_ORIENTATION_USER_PORTRAIT)
        )
    }