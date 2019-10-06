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

@file:JvmName("ResponseUtils")

package com.arthurivanets.commons.data.util

import com.arthurivanets.commons.data.exceptions.NoResultError
import com.arthurivanets.commons.data.exceptions.ResponseError


/**
 *
 */
inline fun <T> resultOrError(block : () -> T?) : Response<T, Throwable> {
    var result : T? = null
    var error : Throwable? = null

    try {
        result = block()
    } catch(throwable : Throwable) {
        error = throwable
    }

    return Response(
        result = result,
        error = error
    )
}


/**
 * Executes the specified mapper if the current [Response] has a valid result.
 *
 * @param mapper the mapper to be executed
 * @return the mapped value, or null
 */
inline fun <I, O> Response<I, Throwable>.mapResult(mapper : (I) -> O) : O? {
    return this.result?.let(mapper)
}


/**
 * Executes the specified mapper if the current [Response] has a valid error.
 *
 * @param mapper the mapper to be executed
 * @return the mapped value, or null
 */
inline fun <O> Response<*, Throwable>.mapError(mapper : (Throwable) -> O) : O? {
    return this.error?.let(mapper)
}


/**
 *
 */
fun <T> T.asResult() : Response<T, Throwable> {
    return Response.result(this)
}


/**
 *
 */
fun <R, E : Throwable> E.asError() : Response<R, Throwable> {
    return Response.error(this)
}


/**
 *
 */
fun <T> Response<T, Throwable>.asError() : Response<T, Throwable> {
    return Response.error(this.errorOrDefault())
}


/**
 *
 */
fun <T> Response<T, Throwable>.asErrorOrNullResponse() : Response<T, Throwable> {
    return Response.error(if(!this.isErroneous && !this.hasResult) NoResultError("Result is null.") else this.errorOrDefault())
}


/**
 *
 */
fun Response<*, Throwable>.errorOrDefault() : Throwable {
    return (this.error ?: ResponseError())
}


/**
 *
 */
fun Response<*, Throwable>.isErroneousOrNullResponse() : Boolean {
    return (this.isErroneous || !this.hasResult)
}


/**
 *
 */
fun <T : Collection<*>> Response<T, Throwable>.isEmptyResponse() : Boolean {
    return (this.result?.isEmpty() ?: true)
}