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

@file:JvmName("RxUtils")

package com.arthurivanets.commons.data.rx.ktx

import com.arthurivanets.commons.PropagatableError
import com.arthurivanets.commons.data.exceptions.NoResultError
import com.arthurivanets.commons.data.util.*
import com.arthurivanets.commons.rx.ktx.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.net.UnknownHostException


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.toObservableSuccessfulResponseOrError() : Observable<Response<T, Throwable>> {
    return this.toObservable()
        .successfulResponseOrError()
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.toFlowableSuccessfulResponseOrError() : Flowable<Response<T, Throwable>> {
    return this.toFlowable()
        .successfulResponseOrError()
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.toMaybeSuccessfulResponseOrError() : Maybe<Response<T, Throwable>> {
    return this.toMaybe()
        .successfulResponseOrError()
}


/**
 *
 */
fun <T> Observable<Response<T, Throwable>>.resultOrError() : Observable<T> {
    return this.flatMapOrError { Observable.just(it) }
}


/**
 *
 */
fun <T> Flowable<Response<T, Throwable>>.resultOrError() : Flowable<T> {
    return this.flatMapOrError { Flowable.just(it) }
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.resultOrError() : Single<T> {
    return this.flatMapOrError { Single.just(it) }
}


/**
 *
 */
fun <T> Maybe<Response<T, Throwable>>.resultOrError() : Maybe<T> {
    return this.flatMapOrError { Maybe.just(it) }
}


/**
 *
 */
fun <T> Observable<Response<T, Throwable>>.successfulResponseOrError() : Observable<Response<T, Throwable>> {
    return this.flatMapResponseOrErrorOut { Observable.just(it) }
}


/**
 *
 */
fun <T> Flowable<Response<T, Throwable>>.successfulResponseOrError() : Flowable<Response<T, Throwable>> {
    return this.flatMapResponseOrErrorOut { Flowable.just(it) }
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.successfulResponseOrError() : Single<Response<T, Throwable>> {
    return this.flatMapResponseOrErrorOut { Single.just(it) }
}


/**
 *
 */
fun <T> Maybe<Response<T, Throwable>>.successfulResponseOrError() : Maybe<Response<T, Throwable>> {
    return this.flatMapResponseOrErrorOut { Maybe.just(it) }
}


/**
 *
 */
inline fun <T> Observable<Response<T, Throwable>>.alsoWithResult(crossinline action : (T) -> Unit) : Observable<Response<T, Throwable>> {
    return this.flatMapOrError {
        action(it)
        return@flatMapOrError it.asResult().asObservable()
    }
}


/**
 *
 */
inline fun <T> Flowable<Response<T, Throwable>>.alsoWithResult(crossinline action : (T) -> Unit) : Flowable<Response<T, Throwable>> {
    return this.flatMapOrError {
        action(it)
        return@flatMapOrError it.asResult().asFlowable()
    }
}


/**
 *
 */
inline fun <T> Single<Response<T, Throwable>>.alsoWithResult(crossinline action : (T) -> Unit) : Single<Response<T, Throwable>> {
    return this.flatMapOrError {
        action(it)
        return@flatMapOrError it.asResult().asSingle()
    }
}


/**
 *
 */
inline fun <T> Maybe<Response<T, Throwable>>.alsoWithResult(crossinline action : (T) -> Unit) : Maybe<Response<T, Throwable>> {
    return this.flatMapOrError {
        action(it)
        return@flatMapOrError it.asResult().asMaybe()
    }
}


/**
 *
 */
inline fun <T, A, R> Observable<Response<T, Throwable>>.flatMapOrError(argument : A, crossinline mappingFunction : (A, T) -> Observable<R>) : Observable<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Observable.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Observable.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(argument, it.result!!)
        }
    }
}


/**
 *
 */
inline fun <T, A, R> Flowable<Response<T, Throwable>>.flatMapWithArgumentOrError(argument : A, crossinline mappingFunction : (A, T) -> Flowable<R>) : Flowable<R> {
    return this.flatMapOrError { mappingFunction(argument, it) }
}


/**
 *
 */
inline fun <T, A, R> Single<Response<T, Throwable>>.flatMapWithArgumentOrError(argument : A, crossinline mappingFunction : (A, T) -> Single<R>) : Single<R> {
    return this.flatMapOrError { mappingFunction(argument, it) }
}


/**
 *
 */
inline fun <T, A, R> Maybe<Response<T, Throwable>>.flatMapWithArgumentOrError(argument : A, crossinline mappingFunction : (A, T) -> Maybe<R>) : Maybe<R> {
    return this.flatMapOrError { mappingFunction(argument, it) }
}


/**
 *
 */
inline fun <T, R> Observable<Response<T, Throwable>>.flatMapOrError(crossinline mappingFunction : (T) -> Observable<R>) : Observable<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Observable.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Observable.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it.result!!)
        }
    }
}


/**
 *
 */
inline fun <T, R> Flowable<Response<T, Throwable>>.flatMapOrError(crossinline mappingFunction : (T) -> Flowable<R>) : Flowable<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Flowable.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Flowable.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it.result!!)
        }
    }
}


/**
 *
 */
inline fun <T, R> Single<Response<T, Throwable>>.flatMapOrError(crossinline mappingFunction : (T) -> Single<R>) : Single<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Single.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Single.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it.result!!)
        }
    }
}


/**
 *
 */
inline fun <T, R> Maybe<Response<T, Throwable>>.flatMapOrError(crossinline mappingFunction : (T) -> Maybe<R>) : Maybe<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Maybe.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Maybe.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it.result!!)
        }
    }
}


/**
 *
 */
inline fun <T, R> Observable<Response<T, Throwable>>.flatMapResponseOrErrorOut(crossinline mappingFunction : (Response<T, Throwable>) -> Observable<R>) : Observable<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Observable.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Observable.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it)
        }
    }
}


/**
 *
 */
inline fun <T, R> Flowable<Response<T, Throwable>>.flatMapResponseOrErrorOut(crossinline mappingFunction : (Response<T, Throwable>) -> Flowable<R>) : Flowable<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Flowable.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Flowable.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it)
        }
    }
}


/**
 *
 */
inline fun <T, R> Single<Response<T, Throwable>>.flatMapResponseOrErrorOut(crossinline mappingFunction : (Response<T, Throwable>) -> Single<R>) : Single<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Single.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Single.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it)
        }
    }
}


/**
 *
 */
inline fun <T, R> Maybe<Response<T, Throwable>>.flatMapResponseOrErrorOut(crossinline mappingFunction : (Response<T, Throwable>) -> Maybe<R>) : Maybe<R> {
    return this.flatMap {
        if(it.isErroneous) {
            Maybe.error(it.errorOrDefault())
        } else if(!it.hasResult) {
            Maybe.error(NoResultError("The Response Result is null."))
        } else {
            mappingFunction(it)
        }
    }
}


/**
 *
 */
fun <T : Collection<*>> Observable<Response<T, Throwable>>.withNonEmptyResult() : Observable<Response<T, Throwable>> {
    return this.filter { !it.isEmptyResponse() }
}


/**
 *
 */
fun <T : Collection<*>> Flowable<Response<T, Throwable>>.withNonEmptyResult() : Flowable<Response<T, Throwable>> {
    return this.filter { !it.isEmptyResponse() }
}


/**
 *
 */
fun <T : Collection<*>> Single<Response<T, Throwable>>.withNonEmptyResult() : Maybe<Response<T, Throwable>> {
    return this.filter { !it.isEmptyResponse() }
}


/**
 *
 */
fun <T : Collection<*>> Maybe<Response<T, Throwable>>.withNonEmptyResult() : Maybe<Response<T, Throwable>> {
    return this.filter { !it.isEmptyResponse() }
}


/**
 *
 */
fun <T> Observable<Response<T, Throwable>>.withResult() : Observable<Response<T, Throwable>> {
    return this.filter { it.hasResult }
}


/**
 *
 */
fun <T> Flowable<Response<T, Throwable>>.withResult() : Flowable<Response<T, Throwable>> {
    return this.filter { it.hasResult }
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.withResult() : Maybe<Response<T, Throwable>> {
    return this.filter { it.hasResult }
}


/**
 *
 */
fun <T> Maybe<Response<T, Throwable>>.withResult() : Maybe<Response<T, Throwable>> {
    return this.filter { it.hasResult }
}


/**
 *
 */
fun <T> Observable<Response<T, Throwable>>.handleRetries(retryCount : Int = DEFAULT_RETRY_COUNT,
                                                         delayCreator : DelayCreator = DelayCreators.exponentialBackoff(DEFAULT_RETRY_DELAY_IN_SECONDS)) : Observable<Response<T, Throwable>> {
    return this.retryWhen {
        it.createRetryInfo(retryCount).flatMap { (error, retryNumber) ->
            when(error) {
                is PropagatableError,
                is UnknownHostException -> throw error
                else -> Observable.timer(delayCreator.create(retryNumber), delayCreator.outTimeUnit)
            }
        }
    }
}


/**
 *
 */
fun <T> Flowable<Response<T, Throwable>>.handleRetries(retryCount : Int = DEFAULT_RETRY_COUNT,
                                                       delayCreator : DelayCreator = DelayCreators.exponentialBackoff(DEFAULT_RETRY_DELAY_IN_SECONDS)) : Flowable<Response<T, Throwable>> {
    return this.retryWhen {
        it.createRetryInfo(retryCount).flatMap { (error, retryNumber) ->
            when(error) {
                is PropagatableError,
                is UnknownHostException -> throw error
                else -> Flowable.timer(delayCreator.create(retryNumber), delayCreator.outTimeUnit)
            }
        }
    }
}


/**
 *
 */
fun <T> Single<Response<T, Throwable>>.handleRetries(retryCount : Int = DEFAULT_RETRY_COUNT,
                                                     delayCreator : DelayCreator = DelayCreators.exponentialBackoff(DEFAULT_RETRY_DELAY_IN_SECONDS)) : Single<Response<T, Throwable>> {
    return this.retryWhen {
        it.createRetryInfo(retryCount).flatMap { (error, retryNumber) ->
            when(error) {
                is PropagatableError,
                is UnknownHostException -> throw error
                else -> Flowable.timer(delayCreator.create(retryNumber), delayCreator.outTimeUnit)
            }
        }
    }
}


/**
 *
 */
fun <T> Maybe<Response<T, Throwable>>.handleRetries(retryCount : Int = DEFAULT_RETRY_COUNT,
                                                    delayCreator : DelayCreator = DelayCreators.exponentialBackoff(DEFAULT_RETRY_DELAY_IN_SECONDS)) : Maybe<Response<T, Throwable>> {
    return this.retryWhen {
        it.createRetryInfo(retryCount).flatMap { (error, retryNumber) ->
            when(error) {
                is PropagatableError,
                is UnknownHostException -> throw error
                else -> Flowable.timer(delayCreator.create(retryNumber), delayCreator.outTimeUnit)
            }
        }
    }
}


/**
 *
 */
fun Observable<Throwable>.createRetryInfo(retryCount : Int) : Observable<Pair<Throwable, Int>> {
    return this.zipWith(
        Observable.range(1, retryCount),
        combineResults()
    )
}


/**
 *
 */
fun Flowable<Throwable>.createRetryInfo(retryCount : Int) : Flowable<Pair<Throwable, Int>> {
    return this.zipWith(
        Flowable.range(1, retryCount),
        combineResults()
    )
}


/**
 *
 */
fun <R1, R2> combineResponses() : BiFunction<R1, R2, Response<Pair<R1, R2>, Throwable>> {
    return BiFunction { result1, result2 -> Pair(result1, result2).asResult() }
}