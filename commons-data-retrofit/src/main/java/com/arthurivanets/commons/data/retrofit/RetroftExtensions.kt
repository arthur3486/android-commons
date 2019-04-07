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

@file:JvmName("RetrofitUtils")

package com.arthurivanets.commons.data.retrofit

import com.arthurivanets.commons.data.util.Response
import retrofit2.Call
import retrofit2.HttpException


/**
 * Executes the Retrofit API Call and handles the response using the specified handler.
 *
 * @param resultHandler the result handler using which the call response should be handled
 * @return the result of the API Call (i.e. [Response])
 */
inline fun <T, R : Any> Call<T>.execute(crossinline resultHandler : (T) -> Response<R, Throwable>) : Response<R, Throwable> {
    val response = this.execute()

    return if(response.isSuccessful) {
        (response.body()?.let { resultHandler(it) } ?: Response.error("Received no response From the Server."))
    } else {
        Response.error(HttpException(response))
    }
}