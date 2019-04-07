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

package com.arthurivanets.commons.network.util

import com.arthurivanets.commons.network.NetworkMonitor
import com.arthurivanets.commons.network.events.NetCapabilities
import com.arthurivanets.commons.network.events.NetState

/**
 *
 */
internal class CallbackRegistry : NetworkMonitor.Callback {


    private val callbacks = HashSet<NetworkMonitor.Callback>()


    /**
     *
     */
    fun add(callback : NetworkMonitor.Callback) {
        callbacks.add(callback)
    }


    /**
     *
     */
    fun remove(callback : NetworkMonitor.Callback) {
        callbacks.remove(callback)
    }


    /**
     *
     */
    fun clear() {
        callbacks.clear()
    }


    override fun onNetworkStateChanged(netState : NetState) {
        callbacks.forEach { it.onNetworkStateChanged(netState) }
    }


    override fun onNetworkCapabilitiesChanged(netCapabilities : NetCapabilities) {
        callbacks.forEach { it.onNetworkCapabilitiesChanged(netCapabilities) }
    }


}