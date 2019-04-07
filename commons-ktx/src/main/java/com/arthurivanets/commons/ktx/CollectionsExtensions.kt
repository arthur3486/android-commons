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

@file:JvmName("CollectionsUtils")

package com.arthurivanets.commons.ktx

import java.util.*
import kotlin.collections.ArrayList


/**
 *
 */
fun <T> List<T>.randomItem() : T? {
    return (if(!this.isEmpty()) this[0.randomUpTo(this.size - 1)] else null)
}


/**
 *
 */
fun <T> Array<T>.randomItem() : T? {
    return (if(!this.isEmpty()) this[0.randomUpTo(this.size - 1)] else null)
}


/**
 *
 */
inline fun <T> List<T>.forEachInRange(startIndex : Int, endIndex : Int, block : (T) -> Unit) {
    for(index in Math.max(startIndex, 0)..Math.min(endIndex, (size - 1))) {
        block(this[index])
    }
}


/**
 *
 */
inline fun <DS, IT> DS.forEachReversed(action : (list : DS, index : Int, item : IT) -> Unit) where DS : List<IT>, IT : Any {
    var index = this.size

    while(index-- > 0) {
        action(this, index, this[index])
    }
}


/**
 *
 */
fun <T> List<T>.firstOrDefault(defaultValue : T) : T {
    return (if(this.isNotEmpty()) this[0] else defaultValue)
}


/**
 * Creates the [HashSet] out of the current [List].
 * (Uses the specified entity creator to properly create the new dataset entities based on the corresponding item)
 *
 * @param entityCreator the exact entity creator used to create the dataset entities out of the corresponding items
 * @return the created [HashSet]
 */
inline fun <ET, IT> Collection<IT>.createHashSet(entityCreator : (item : IT) -> ET) : Set<ET> {
    return HashSet<ET>().also { set ->
        for(item in this) {
            set.add(entityCreator(item))
        }
    }
}


/**
 * Creates the [HashSet] out of the current [List].
 * (Uses the specified entity creator to properly create the new dataset entities based on the corresponding item)
 *
 * @param entityCreator the exact entity creator used to create the dataset entities out of the corresponding items
 * @return the created [HashSet]
 */
inline fun <ET, IT> List<IT>.createHashSet(entityCreator : (index : Int, item : IT) -> ET) : Set<ET> {
    return HashSet<ET>().also { set ->
        for((index, item) in this.withIndex()) {
            set.add(entityCreator(index, item))
        }
    }
}


/**
 * Extracts the unique items from the current dataset.
 * (Uses the specified key extractor to determine the item uniqueness)
 *
 * @param presenceTracker pre-extracted item keys for presence tracking purposes
 * @param keyExtractor the extractor to be used for the item key extraction
 * @return the [List] of extracted unique items
 */
inline fun <DS : Collection<IT>, KT, IT> DS.extractUnique(presenceTracker : Set<KT>, keyExtractor : (IT) -> KT) : List<IT> {
    if(this.isEmpty()) {
        return ArrayList()
    }

    val extractedUniqueItems = ArrayList<IT>()

    for(item in this) {
        keyExtractor(item).let { key ->
            if(!presenceTracker.contains(key)) {
                extractedUniqueItems.add(item)
            }
        }
    }

    return extractedUniqueItems
}


/**
 *
 */
fun <DS : MutableList<IT>, IT> DS.append(items : Array<out IT>) : DS {
    this.addAll(items)
    return this
}


/**
 *
 */
fun <DS : MutableList<IT>, IT> DS.append(items : Iterable<IT>) : DS = apply {
    this.addAll(items)
}


/**
 *
 */
fun <DS : MutableList<IT>, IT> DS.addAllFromTop(topOffset : Int = 0, items : List<IT>) : DS = apply {
    for(i in (items.size - 1) downTo 0) {
        this.add(topOffset, items[i])
    }
}


/**
 *
 */
fun <T> Stack<T>.pushAll(items : Collection<T>) : Stack<T> = apply {
    items.forEach { push(it) }
}


/**
 *
 */
inline fun <T> Queue<T>.drainQueue(crossinline consumer : (T) -> Unit) {
    while(!this.isEmpty()) {
        this.remove()?.let { consumer(it) }
    }
}


/**
 *
 */
fun <T, S> Queue<T>.drainQueueInto(sink : S) : S where S : MutableCollection<T> {
    this.drainQueue { sink.add(it) }
    return sink
}


/**
 *
 */
fun <T> Queue<T>.drainQueueInto(sink : Array<T>) : Array<T> {
    require(sink.size == this.size) { "The Sink Array must be of the exact same size as the source Queue." }

    this.drainQueue { sink[sink.size] = it }

    return sink
}


/**
 *
 */
inline fun <T> Deque<T>.drainStack(crossinline consumer : (T) -> Unit) {
    while(!this.isEmpty()) {
        this.removeLast()?.let { consumer(it) }
    }
}


/**
 *
 */
inline fun <T> Stack<T>.drain(crossinline consumer : (T) -> Unit) {
    while(!this.isEmpty()) {
        this.pop()?.let { consumer(it) }
    }
}


/**
 *
 */
inline fun <DS, IT> DS.toCSVString(propertyExtractor : (IT) -> Any? = { it }) : String where DS : Collection<IT>, IT : Any {
    val stringBuilder = StringBuilder()
    var isFirstItem = true

    for(item in this) {
        if(!isFirstItem) {
            stringBuilder.append(",")
        }

        stringBuilder.append(propertyExtractor(item))

        isFirstItem = false
    }

    return stringBuilder.toString()
}


/**
 *
 */
fun <T, R> R?.default(defaultValue : R) : R where R : Collection<T> {
    return (this ?: defaultValue)
}


/**
 * Clamps the specified index based on the constraints imposed by the current dataset ([List]).
 *
 * @return the clamped index, or -1 if the [List] is empty
 */
fun List<*>.clampIndex(index : Int) : Int {
    return (if(!this.isEmpty()) index.clamp(0, this.lastIndex) else -1)
}


/**
 *
 */
fun List<*>.isValidFirstItemIndex(offset : Int = 0) : Boolean {
    return this.isValidIndex(offset)
}


/**
 *
 */
fun List<*>.isValidLastItemIndex(offset : Int = 0) : Boolean {
    return this.isValidIndex(this.lastIndex - offset)
}


/**
 *
 */
fun List<*>.isValidIndex(index : Int) : Boolean {
    return ((index >= 0) && (index < this.size))
}