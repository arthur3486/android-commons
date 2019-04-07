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

@file:JvmName("StreamUtils")

package com.arthurivanets.commons.ktx

import java.io.InputStream
import java.io.OutputStream


/**
 * Manages the lifecycle of the created [OutputStream], while at the same time
 * allows the aforementioned stream to be used in a safe manner.
 *
 * @param outputStreamCreator the creator of the [OutputStream]
 * @param streamHandler the stream manipulator
 * @param errorHandler the error handler (consumer)
 * @param completionHandler the action to be performed at the very end
 * @return <strong>true</strong> if the stream was handled successfully, <strong>false</strong> otherwise
 */
inline fun manageStream(outputStreamCreator : () -> OutputStream,
                        streamHandler : (OutputStream) -> Unit,
                        errorHandler : (Throwable) -> Unit = { throw it },
                        completionHandler : () -> Unit = {}) : Boolean {
    var outputStream : OutputStream? = null
    var succeeded = false

    try {
        outputStream = outputStreamCreator().apply {
            streamHandler(this)
            flush()

            succeeded = true
        }
    } catch(throwable : Throwable) {
        errorHandler(throwable)
    } finally {
        consumeError({ outputStream?.close() })
        completionHandler()
    }

    return succeeded
}


/**
 * Manages the lifecycle of the created [InputStream] and [OutputStream], while
 * at the same time allows the aforementioned streams to be used in a safe manner.
 *
 * @param inputStreamCreator the creator of the [InputStream]
 * @param outputStreamCreator the creator of the [OutputStream]
 * @param streamHandler the stream manipulator
 * @param errorHandler the error handler (consumer)
 * @return <strong>true</strong> if the streams were handled successfully, <strong>false</strong> otherwise
 */
inline fun manageStreams(inputStreamCreator : () -> InputStream,
                         outputStreamCreator : () -> OutputStream,
                         streamHandler : (InputStream, OutputStream) -> Unit,
                         errorHandler : (Throwable) -> Unit = { throw it }) : Boolean {
    var inputStream : InputStream? = null
    var outputStream : OutputStream? = null
    var succeeded = false

    try {
        inputStream = inputStreamCreator()
        outputStream = outputStreamCreator()

        streamHandler(inputStream, outputStream)

        outputStream.flush()

        succeeded = true
    } catch(throwable : Throwable) {
        errorHandler(throwable)
    } finally {
        consumeError({ inputStream?.close() })
        consumeError({ outputStream?.close() })
    }

    return succeeded
}


/**
 * Transfers (writes) the data bytes from the current [InputStream] into the specified [OutputStream].
 *
 * @param outputStream the data sink
 * @param bufferSize the buffer size (data chunk size in bytes)
 */
fun InputStream.transferBytes(outputStream : OutputStream,
                              bufferSize : Int = 4096) {
    val buffer = ByteArray(bufferSize)
    var readByteCount = this.read(buffer)

    while(readByteCount != -1) {
        outputStream.write(buffer, 0, readByteCount)
        readByteCount = this.read(buffer)
    }
}