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

package com.arthurivanets.commons.data.util

import com.arthurivanets.commons.data.exceptions.ResponseError

/**
 *
 */
data class Response<R, E : Throwable>(
    val result : R? = null,
    val error : E? = null
) {


    /**
     *
     */
    val hasResult = (result != null)

    /**
     *
     */
    val isErroneous = (error != null)


    companion object {

        /**
         *
         */
        @JvmStatic fun <R> result(result : R) : Response<R, Throwable> {
            return Response(result = result)
        }

        /**
         *
         */
        @JvmStatic fun <R> error(error : Throwable) : Response<R, Throwable> {
            return Response(error = error)
        }

        /**
         *
         */
        @JvmStatic fun <R> error(errorMessage : String) : Response<R, Throwable> {
            return error(ResponseError(errorMessage))
        }

    }


}