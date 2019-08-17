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

package com.arthurivanets.commons.rx.ktx

import com.arthurivanets.commons.rx.schedulers.SchedulerProvider
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


/**
 *
 */
inline fun <T> Observable<T>.alsoDo(crossinline action : (T) -> Unit) : Observable<T> {
    return this.flatMap {
        action(it)
        return@flatMap it.asObservable()
    }
}


/**
 *
 */
inline fun <T> Flowable<T>.alsoDo(crossinline action : (T) -> Unit) : Flowable<T> {
    return this.flatMap {
        action(it)
        return@flatMap it.asFlowable()
    }
}


/**
 *
 */
inline fun <T> Single<T>.alsoDo(crossinline action : (T) -> Unit) : Single<T> {
    return this.flatMap {
        action(it)
        return@flatMap it.asSingle()
    }
}


/**
 *
 */
inline fun <T> Maybe<T>.alsoDo(crossinline action : (T) -> Unit) : Maybe<T> {
    return this.flatMap {
        action(it)
        return@flatMap it.asMaybe()
    }
}


/**
 *
 */
fun <R> Observable<*>.emitResult(result : R) : Observable<R> {
    return this.flatMap { result.asObservable() }
}


/**
 *
 */
fun <R> Flowable<*>.emitResult(result : R) : Flowable<R> {
    return this.flatMap { result.asFlowable() }
}


/**
 *
 */
fun <R> Single<*>.emitResult(result : R) : Single<R> {
    return this.flatMap { result.asSingle() }
}


/**
 *
 */
fun <R> Maybe<*>.emitResult(result : R) : Maybe<R> {
    return this.flatMap { result.asMaybe() }
}


/**
 *
 */
fun <T> Observable<T>.subscribeOnIO() : Observable<T> {
    return this.subscribeOn(Schedulers.io())
}


/**
 *
 */
fun <T> Flowable<T>.subscribeOnIO() : Flowable<T> {
    return this.subscribeOn(Schedulers.io())
}


/**
 *
 */
fun <T> Single<T>.subscribeOnIO() : Single<T> {
    return this.subscribeOn(Schedulers.io())
}


/**
 *
 */
fun <T> Maybe<T>.subscribeOnIO() : Maybe<T> {
    return this.subscribeOn(Schedulers.io())
}


/**
 *
 */
fun Completable.subscribeOnIO() : Completable {
    return this.subscribeOn(Schedulers.io())
}


/**
 *
 */
fun <T> Observable<T>.subscribeOnComputation() : Observable<T> {
    return this.subscribeOn(Schedulers.computation())
}


/**
 *
 */
fun <T> Flowable<T>.subscribeOnComputation() : Flowable<T> {
    return this.subscribeOn(Schedulers.computation())
}


/**
 *
 */
fun <T> Single<T>.subscribeOnComputation() : Single<T> {
    return this.subscribeOn(Schedulers.computation())
}


/**
 *
 */
fun <T> Maybe<T>.subscribeOnComputation() : Maybe<T> {
    return this.subscribeOn(Schedulers.computation())
}


/**
 *
 */
fun Completable.subscribeOnComputation() : Completable {
    return this.subscribeOn(Schedulers.computation())
}


/**
 *
 */
fun <T> Observable<T>.observeOnUI() : Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}


/**
 *
 */
fun <T> Flowable<T>.observeOnUI() : Flowable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}


/**
 *
 */
fun <T> Single<T>.observeOnUI() : Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}


/**
 *
 */
fun <T> Maybe<T>.observeOnUI() : Maybe<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}


/**
 *
 */
fun Completable.observeOnUI() : Completable {
    return this.observeOn(AndroidSchedulers.mainThread())
}


/**
 *
 */
fun <T> Observable<T>.applyIOWorkSchedulers(schedulerProvider : SchedulerProvider) : Observable<T> {
    return this.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Flowable<T>.applyIOWorkSchedulers(schedulerProvider : SchedulerProvider) : Flowable<T> {
    return this.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Single<T>.applyIOWorkSchedulers(schedulerProvider : SchedulerProvider) : Single<T> {
    return this.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Maybe<T>.applyIOWorkSchedulers(schedulerProvider : SchedulerProvider) : Maybe<T> {
    return this.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun Completable.applyIOWorkSchedulers(schedulerProvider : SchedulerProvider) : Completable {
    return this.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Observable<T>.applyCPUWorkSchedulers(schedulerProvider : SchedulerProvider) : Observable<T> {
    return this.subscribeOn(schedulerProvider.computation)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Flowable<T>.applyCPUWorkSchedulers(schedulerProvider : SchedulerProvider) : Flowable<T> {
    return this.subscribeOn(schedulerProvider.computation)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Single<T>.applyCPUWorkSchedulers(schedulerProvider : SchedulerProvider) : Single<T> {
    return this.subscribeOn(schedulerProvider.computation)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Maybe<T>.applyCPUWorkSchedulers(schedulerProvider : SchedulerProvider) : Maybe<T> {
    return this.subscribeOn(schedulerProvider.computation)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun Completable.applyCPUWorkSchedulers(schedulerProvider : SchedulerProvider) : Completable {
    return this.subscribeOn(schedulerProvider.computation)
        .observeOn(schedulerProvider.ui)
}


/**
 *
 */
fun <T> Observable<T>.applyIOWorkSchedulers() : Observable<T> {
    return this.subscribeOnIO()
        .observeOnUI()
}


/**
 *
 */
fun <T> Flowable<T>.applyIOWorkSchedulers() : Flowable<T> {
    return this.subscribeOnIO()
        .observeOnUI()
}


/**
 *
 */
fun <T> Single<T>.applyIOWorkSchedulers() : Single<T> {
    return this.subscribeOnIO()
        .observeOnUI()
}


/**
 *
 */
fun <T> Maybe<T>.applyIOWorkSchedulers() : Maybe<T> {
    return this.subscribeOnIO()
        .observeOnUI()
}


/**
 *
 */
fun Completable.applyIOWorkSchedulers() : Completable {
    return this.subscribeOnIO()
        .observeOnUI()
}


/**
 *
 */
fun <T> Observable<T>.applyCPUWorkSchedulers() : Observable<T> {
    return this.subscribeOnComputation()
        .observeOnUI()
}


/**
 *
 */
fun <T> Flowable<T>.applyCPUWorkSchedulers() : Flowable<T> {
    return this.subscribeOnComputation()
        .observeOnUI()
}


/**
 *
 */
fun <T> Single<T>.applyCPUWorkSchedulers() : Single<T> {
    return this.subscribeOnComputation()
        .observeOnUI()
}


/**
 *
 */
fun <T> Maybe<T>.applyCPUWorkSchedulers() : Maybe<T> {
    return this.subscribeOnComputation()
        .observeOnUI()
}


/**
 *
 */
fun Completable.applyCPUWorkSchedulers() : Completable {
    return this.subscribeOnComputation()
        .observeOnUI()
}


/**
 *
 */
fun <T> T.asObservable() : Observable<T> {
    return Observable.just(this)
}


/**
 *
 */
fun <T> T.asFlowable() : Flowable<T> {
    return Flowable.just(this)
}


/**
 *
 */
fun <T> T.asSingle() : Single<T> {
    return Single.just(this)
}


/**
 *
 */
fun <T> T.asMaybe() : Maybe<T> {
    return Maybe.just(this)
}


/**
 *
 */
fun <R1, R2> combineResults() : BiFunction<R1, R2, Pair<R1, R2>> {
    return BiFunction { result1, result2 -> Pair(result1, result2) }
}


/**
 *
 */
fun <T, R : T> Consumer<T>.adapt() : Consumer<R> {
    return Consumer(::accept)
}