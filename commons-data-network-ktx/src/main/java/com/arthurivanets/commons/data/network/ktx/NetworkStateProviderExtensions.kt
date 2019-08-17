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

@file:JvmName("NetworkStateProviderUtils")

package com.arthurivanets.commons.data.network.ktx

import com.arthurivanets.commons.data.rx.ktx.flatMapOrError
import com.arthurivanets.commons.data.util.Response
import com.arthurivanets.commons.data.util.errorOrDefault
import com.arthurivanets.commons.network.NetworkStateProvider
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


/**
 *
 */
fun <T> NetworkStateProvider.ifNetworkAvailable(observable : Observable<T>) : Observable<T> {
    return Observable.just(getNetworkAvailability())
        .flatMapOrError { observable }
}


/**
 *
 */
fun <T> NetworkStateProvider.ifNetworkAvailable(flowable : Flowable<T>) : Flowable<T> {
    return Flowable.just(getNetworkAvailability())
        .flatMapOrError { flowable }
}


/**
 *
 */
fun <T> NetworkStateProvider.ifNetworkAvailable(single : Single<T>) : Single<T> {
    return Single.just(getNetworkAvailability())
        .flatMapOrError { single }
}


/**
 *
 */
fun <T> NetworkStateProvider.ifNetworkAvailable(maybe : Maybe<T>) : Maybe<T> {
    return Maybe.just(getNetworkAvailability())
        .flatMapOrError { maybe }
}


/**
 *
 */
fun NetworkStateProvider.errorOutIfNetworkUnavailable() {
    this.getNetworkAvailability().let {
        if(it.isErroneous) {
            throw it.errorOrDefault()
        }
    }
}


/**
 * To be used for RxJava flows.
 */
fun NetworkStateProvider.getNetworkAvailability() : Response<Boolean, Throwable> {
    return if(this.isNetworkAvailable) {
        Response.result(true)
    } else {
        Response(
            result = false,
            error = IllegalStateException("The Network is unavailable. Unable to perform the action.")
        )
    }
}