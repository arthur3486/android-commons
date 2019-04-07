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

@file:JvmName("BitmapUtils")

package com.arthurivanets.commons.ktx

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.IntRange
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


/**
 * Saves the [Bitmap] into a specified output [File].
 *
 * @param compressFormat the exact format of the compressed image (see [Bitmap.CompressFormat])
 * @param imageQuality the exact quality of the image (a value between 0 and 100) (affects the image file size)
 * @param outputFile the file to output (save) the [Bitmap] into
 * @param recycleBitmap whether to recycle the [Bitmap] after the completion of the saving operation
 */
fun Bitmap.save(compressFormat : Bitmap.CompressFormat,
                @IntRange(from = 0, to = 100) imageQuality : Int,
                outputFile : File,
                recycleBitmap : Boolean = true) {
    manageStream(
        outputStreamCreator = { BufferedOutputStream(FileOutputStream(outputFile)) },
        streamHandler = { this.compress(compressFormat, imageQuality, it) },
        completionHandler = {
            if(recycleBitmap) {
                this.recycle()
            }
        }
    )
}


/**
 * Resizes the current [Bitmap] based on the specified desired width (Resize Type: [ResizeType.WIDTH_BASED]).
 *
 * @param desiredWidth the exact width to base the resizing of the bitmap upon
 * @param recycleOriginal whether to recycle the original [Bitmap] after the completion of the resizing operation
 * @return the actual resized [Bitmap]
 */
fun Bitmap.resizeWidthBased(desiredWidth : Int, recycleOriginal : Boolean = true) : Bitmap {
    return resize(
        resizeType = ResizeType.WIDTH_BASED,
        value = desiredWidth,
        recycleOriginal = recycleOriginal
    )
}


/**
 * Resizes the current [Bitmap] based on the specified desired height (Resize Type: [ResizeType.HEIGHT_BASED]).
 *
 * @param desiredHeight the exact height to base the resizing of the bitmap upon
 * @param recycleOriginal whether to recycle the original [Bitmap] after the completion of the resizing operation
 * @return the actual resized [Bitmap]
 */
fun Bitmap.resizeHeightBased(desiredHeight : Int, recycleOriginal : Boolean = true) : Bitmap {
    return resize(
        resizeType = ResizeType.HEIGHT_BASED,
        value = desiredHeight,
        recycleOriginal = recycleOriginal
    )
}


/**
 * Resizes the current [Bitmap] based on the specified [ResizeType] and the corresponding value.
 *
 * @param resizeType the exact resize type which determines which property the resizing is to be based upon
 * @param value the value to base the resizing upon (either the width or height, depending on the specified [ResizeType])
 * @param recycleOriginal whether to recycle the original [Bitmap] after the completion of the resizing operation
 * @return the actual resized [Bitmap]
 */
fun Bitmap.resize(resizeType : ResizeType,
                  value : Int,
                  recycleOriginal : Boolean = true) : Bitmap {
    if(((ResizeType.WIDTH_BASED == resizeType) && (this.width == value))
        || ((ResizeType.HEIGHT_BASED == resizeType) && (this.height == value))) {
        return this
    }

    val ratio = (if(ResizeType.WIDTH_BASED == resizeType) (value * 1f / this.width) else (value * 1f / this.height))
    val newWidth = (this.width * ratio).toInt()
    val newHeight = (this.height * ratio).toInt()

    val createdBitmap = Bitmap.createScaledBitmap(
        this,
        newWidth,
        newHeight,
        true
    )

    if(recycleOriginal) {
        this.recycle()
    }

    return createdBitmap
}


/**
 * Resizes the current [Bitmap] based on the specified desired width and height.
 *
 * @param desiredWidth the new width
 * @param desiredHeight the new height
 * @param recycleOriginal whether to recycle the original [Bitmap] after the completion of the resizing operation
 * @return the actual resized [Bitmap]
 */
fun Bitmap.resize(desiredWidth : Int,
                  desiredHeight : Int,
                  recycleOriginal : Boolean = true) : Bitmap {
    if((this.width == desiredWidth) && (this.height == desiredHeight)) {
        return this
    }

    val createdBitmap = Bitmap.createScaledBitmap(
        this,
        desiredWidth,
        desiredHeight,
        true
    )

    if(recycleOriginal) {
        this.recycle()
    }

    return createdBitmap
}


/**
 * Crops out the specified central area out of the current [Bitmap].
 *
 * @param desiredWidth the width of the area to be cropped out
 * @param desiredHeight the height of the area to be cropped out
 * @param recycleOriginal whether to recycle the original [Bitmap] after the completion of the cropping operation
 * @return the actual cropped [Bitmap]
 */
fun Bitmap.centerCrop(desiredWidth : Int,
                      desiredHeight : Int,
                      recycleOriginal : Boolean = true) : Bitmap {
    val adjustedWidth = Math.min(this.width, desiredWidth)
    val adjustedHeight = Math.min(this.height, desiredHeight)

    if((this.width == adjustedWidth) && (this.height == adjustedHeight)) {
        return this
    }

    val newX = ((this.width / 2) - (desiredWidth / 2))
    val newY = ((this.height / 2) - (desiredHeight / 2))

    val createdBitmap = Bitmap.createBitmap(
        this,
        newX,
        newY,
        adjustedWidth,
        adjustedHeight
    )

    if(recycleOriginal) {
        this.recycle()
    }

    return createdBitmap
}


/**
 * Decodes the [Bitmap] using the specified decoder and ensuring that only the
 * most optimal decoding practises are used (optimal in terms of memory utilization).
 *
 * @param decoder the decoder using which the actual [Bitmap] is to be decoded
 * @param desiredWidth the desired width (upon which to base the downsampling)
 * @param desiredHeight the desired height (upon which to base the downsampling)
 * @return the actul decoded [Bitmap]
 */
inline fun decodeBitmap(decoder : (BitmapFactory.Options) -> Bitmap?,
                        desiredWidth : Int,
                        desiredHeight : Int) : Bitmap {
    return BitmapFactory.Options().run {
        // decoding only the image info
        inJustDecodeBounds = true

        decoder(this)

        // decoding the actual (full) image with the applied downsampling optimizations
        inSampleSize = calculateInSampleSize(desiredWidth, desiredHeight)
        inJustDecodeBounds = false

        return@run decoder(this)!!
    }
}


/**
 * Calculates the downsampling ratio based on the specified desired width and height.
 * (To be used for the optimal (in terms of memory utilization) image decoding)
 *
 * @param desiredWidth the width to base the calculations on
 * @param desiredHeight the height to base the calculations on
 * @return the calculated downsampling ratio
 */
fun BitmapFactory.Options.calculateInSampleSize(desiredWidth : Int, desiredHeight : Int) : Int {
    val width = this.outWidth
    val height = this.outHeight
    val canDownsampleWidthwise = ((desiredWidth > 0) && (width > desiredWidth))
    val canDownsampleHeightwise = ((desiredHeight > 0) && (height > desiredHeight))
    var inSampleSize = 1

    if(canDownsampleWidthwise || canDownsampleHeightwise) {
        val halfWidth = (width / 2)
        val halfHeight = (height / 2)

        while(((halfWidth / inSampleSize) >= desiredWidth) && ((halfHeight / inSampleSize) >= desiredHeight)) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}


/**
 * A set of Resize Types for [Bitmap] resizing related purposes.
 */
enum class ResizeType {

    WIDTH_BASED,
    HEIGHT_BASED

}