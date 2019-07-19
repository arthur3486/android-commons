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

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.arthurivanets.commons.Permissions
import com.arthurivanets.commons.SdkVersions
import com.arthurivanets.commons.ktx.connectivityManager
import com.arthurivanets.commons.network.events.NetCapabilities
import com.arthurivanets.commons.network.events.NetState
import com.arthurivanets.commons.network.events.NetworkEvent
import com.arthurivanets.commons.network.util.*
import com.arthurivanets.rxbus.android.AndroidRxBus
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 *
 */
@RequiresApi(SdkVersions.LOLLIPOP)
internal class NetworkMonitorImpl(
    context : Context,
    transportTypes : Set<Int> = DEFAULT_TRANSPORT_TYPES
) : NetworkMonitor, ConnectivityManager.NetworkCallback() {


    private val eventBus = AndroidRxBus.newInstance()
    private val applicationContext = context.applicationContext

    private val networkRequest = NetworkRequest.Builder()
        .addTransportTypes(transportTypes)
        .build()

    private val callbackRegistry = CallbackRegistry()

    override var isEnabled = false
        private set


    @RequiresPermission(Permissions.ACCESS_NETWORK_STATE)
    override fun enable() {
        if(!isEnabled) {
            applicationContext.connectivityManager.registerNetworkCallback(networkRequest, this)
            isEnabled = true
        }
    }


    @RequiresPermission(Permissions.ACCESS_NETWORK_STATE)
    override fun disable() {
        if(isEnabled) {
            applicationContext.connectivityManager.unregisterNetworkCallback(this)
            isEnabled = false
        }
    }


    override fun addCallback(callback : NetworkMonitor.Callback) {
        callbackRegistry.add(callback)
    }


    override fun removeCallback(callback : NetworkMonitor.Callback) {
        callbackRegistry.remove(callback)
    }


    override fun clearCallbacks() {
        callbackRegistry.clear()
    }


    @RequiresPermission(Permissions.ACCESS_NETWORK_STATE)
    override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)

        if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            reportNetworkCapabilitiesChange(NetCapabilities(
                linkDownstreamBandwidthKbps = networkCapabilities.linkDownstreamBandwidthKbps,
                linkUpstreamBandwidthKbps = networkCapabilities.linkUpstreamBandwidthKbps,
                isNetworkAvailable = applicationContext.isNetworkConnected,
                isNetworkMetered = applicationContext.isNetworkConnectionMetered,
                isRestricted = networkCapabilities.isRestricted,
                isTrusted = networkCapabilities.isTrusted,
                isVpn = networkCapabilities.isVpn,
                isValidated = networkCapabilities.isValidated
            ))
        }
    }


    @SuppressWarnings("MissingPermission")
    override fun onAvailable(network : Network) {
        super.onAvailable(network)

        reportNetworkStateChange(NetState(
            isNetworkAvailable = true,
            isNetworkMetered = applicationContext.isNetworkConnectionMetered
        ))
    }


    @SuppressWarnings("MissingPermission")
    override fun onLost(network : Network) {
        super.onLost(network)

        reportNetworkStateChange(NetState(
            isNetworkAvailable = false,
            isNetworkMetered = applicationContext.isNetworkConnectionMetered
        ))
    }


    override fun subscribe(eventConsumer : Consumer<NetworkEvent<*>>) : Disposable {
        return eventBus.register(NetworkEvent::class.java, eventConsumer)
    }


    private fun reportNetworkStateChange(netState : NetState) {
        postBusEvent(NetworkEvent.StateChanged(netState))
        callbackRegistry.onNetworkStateChanged(netState)
    }


    private fun reportNetworkCapabilitiesChange(netCapabilities : NetCapabilities) {
        postBusEvent(NetworkEvent.CapabilitiesChanged(netCapabilities))
        callbackRegistry.onNetworkCapabilitiesChanged(netCapabilities)
    }


    private fun postBusEvent(event : NetworkEvent<*>) {
        eventBus.post(event)
    }


}