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

@file:JvmName("NetworkCapabilitiesUtils")

package com.arthurivanets.commons.network.util

import android.annotation.SuppressLint
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.RequiresApi
import com.arthurivanets.commons.SdkInfo
import com.arthurivanets.commons.SdkVersions


/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("isRestricted")
val NetworkCapabilities.isRestricted : Boolean
    get() = !this.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("isTrusted")
val NetworkCapabilities.isTrusted : Boolean
    get() = this.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("isVpn")
val NetworkCapabilities.isVpn : Boolean
    get() = !this.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)

/**
 *
 */
@get:JvmName("isValidated")
val NetworkCapabilities.isValidated : Boolean
    @SuppressLint("InlinedApi")
    get() = (if(SdkInfo.IS_AT_LEAST_MARSHMALLOW) this.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) else false)


/**
 *
 */
@RequiresApi(SdkVersions.LOLLIPOP)
internal fun NetworkRequest.Builder.addTransportTypes(transportTypes : Collection<Int>) : NetworkRequest.Builder = apply {
    transportTypes.forEach { addTransportType(it) }
}