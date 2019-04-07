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

package com.arthurivanets.commons.network

import com.arthurivanets.commons.network.events.NetCapabilities
import com.arthurivanets.commons.network.events.NetState
import com.arthurivanets.commons.network.events.NetworkEvent
import com.arthurivanets.rxbus.EventSource

/**
 *
 */
interface NetworkMonitor : EventSource<NetworkEvent<*>> {


    /**
     *
     */
    fun enable()

    /**
     *
     */
    fun disable()

    /**
     *
     */
    fun addCallback(callback : Callback)

    /**
     *
     */
    fun removeCallback(callback : Callback)

    /**
     *
     */
    fun clearCallbacks()


    /**
     *
     */
    interface Callback {

        /**
         *
         */
        fun onNetworkStateChanged(netState : NetState)

        /**
         *
         */
        fun onNetworkCapabilitiesChanged(netCapabilities : NetCapabilities)

    }


}