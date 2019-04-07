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

@file:JvmName("NetworkUtils")

package com.arthurivanets.commons.network.util

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import com.arthurivanets.commons.Permissions


/**
 *
 */
@get:RequiresPermission(Permissions.ACCESS_NETWORK_STATE)
@get:JvmName("isNetworkConnected")
val Context.isNetworkConnected : Boolean
    get() = ((this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.activeNetworkInfo?.isConnected ?: false)

/**
 *
 */
@get:RequiresPermission(Permissions.ACCESS_NETWORK_STATE)
@get:JvmName("isNetworkConnectionMetered")
val Context.isNetworkConnectionMetered : Boolean
    get() = ((this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.isActiveNetworkMetered ?: false)