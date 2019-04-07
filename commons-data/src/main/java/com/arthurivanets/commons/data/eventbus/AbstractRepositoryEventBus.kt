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

package com.arthurivanets.commons.data.eventbus

import com.arthurivanets.rxbus.BusEvent
import com.arthurivanets.rxbus.android.AndroidRxBus
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 *
 */
abstract class AbstractRepositoryEventBus : RepositoryEventBus {


    private val eventBus = AndroidRxBus.newInstance()


    final override fun postEvent(event : BusEvent<*>) {
        eventBus.post(event)
    }


    final override fun <T : BusEvent<*>> register(eventType : Class<T>, onEvent : Consumer<T>) : Disposable {
        return eventBus.register(eventType, onEvent)
    }


}