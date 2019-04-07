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

package com.arthurivanets.commons

/**
 *
 */
object SdkInfo {

    @JvmStatic val VERSION = SdkVersion.SDK_INT
    @JvmStatic val IS_AT_LEAST_KITKAT = (VERSION >= SdkVersions.KITKAT)
    @JvmStatic val IS_AT_LEAST_LOLLIPOP = (VERSION >= SdkVersions.LOLLIPOP)
    @JvmStatic val IS_AT_LEAST_MARSHMALLOW = (VERSION >= SdkVersions.M)
    @JvmStatic val IS_AT_LEAST_NOUGAT = (VERSION >= SdkVersions.N)
    @JvmStatic val IS_AT_LEAST_OREO = (VERSION >= SdkVersions.O)
    @JvmStatic val IS_AT_LEAST_PIE = (VERSION >= SdkVersions.P)

}