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

import java.util.concurrent.TimeUnit
import kotlin.math.pow

/**
 *
 */
object DelayCreators {


    /**
     *
     */
    @JvmStatic fun constantBackoff(baseDelayInSeconds : Long, outTimeUnit : TimeUnit = TimeUnit.SECONDS) : DelayCreator {
        return DelayCreator(
            baseDelayInSeconds = baseDelayInSeconds,
            outTimeUnit = outTimeUnit,
            creator = { outTimeUnit.convert(baseDelayInSeconds, TimeUnit.SECONDS) }
        )
    }


    /**
     *
     */
    @JvmStatic fun linearBackoff(baseDelayInSeconds : Long, outTimeUnit : TimeUnit = TimeUnit.SECONDS) : DelayCreator {
        return DelayCreator(
            baseDelayInSeconds = baseDelayInSeconds,
            outTimeUnit = outTimeUnit,
            creator = { outTimeUnit.convert((baseDelayInSeconds * it), TimeUnit.SECONDS) }
        )
    }


    /**
     *
     */
    @JvmStatic fun exponentialBackoff(baseDelayInSeconds : Long, outTimeUnit : TimeUnit = TimeUnit.SECONDS) : DelayCreator {
        return DelayCreator(
            baseDelayInSeconds = baseDelayInSeconds,
            outTimeUnit = outTimeUnit,
            creator = { outTimeUnit.convert(baseDelayInSeconds.toDouble().pow(it.toDouble()).toLong(), TimeUnit.SECONDS) }
        )
    }


}