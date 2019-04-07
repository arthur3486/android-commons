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

@file:JvmName("CalendarUtils")

package com.arthurivanets.commons.ktx

import android.content.Context
import java.util.*


/**
 *
 */
val Calendar.year : Int
    get() = this.get(Calendar.YEAR)

/**
 *
 */
val Calendar.month : Int
    get() = this.get(Calendar.MONTH)

/**
 *
 */
val Calendar.dayOfWeek : Int
    get() = this.get(Calendar.DAY_OF_WEEK)

/**
 *
 */
val Calendar.dayOfMonth : Int
    get() = this.get(Calendar.DAY_OF_MONTH)

/**
 *
 */
val Calendar.dayOfYear : Int
    get() = this.get(Calendar.DAY_OF_YEAR)

/**
 *
 */
val Calendar.hour : Int
    get() = this.get(Calendar.HOUR)

/**
 *
 */
val Calendar.hourOfDay : Int
    get() = this.get(Calendar.HOUR_OF_DAY)

/**
 *
 */
val Calendar.minute : Int
    get() = this.get(Calendar.MINUTE)

/**
 *
 */
val Calendar.second : Int
    get() = this.get(Calendar.SECOND)

/**
 *
 */
val Calendar.millisecond : Int
    get() = this.get(Calendar.MILLISECOND)

/**
 *
 */
val Calendar.monthLength : Int
    get() = this.getActualMaximum(Calendar.DAY_OF_MONTH)


/**
 *
 */
fun Context.createCalendar(year : Int,
                           month : Int,
                           dayOfMonth : Int,
                           hourOfDay : Int = 0,
                           minute : Int = 0,
                           second : Int = 0,
                           millisecond : Int = 0) : Calendar {
    return this.locale.createCalendar(
        year = year,
        month = month,
        dayOfMonth = dayOfMonth,
        hourOfDay = hourOfDay,
        minute = minute,
        second = second,
        millisecond = millisecond
    )
}


/**
 *
 */
fun Locale.createCalendar(year : Int,
                          month : Int,
                          dayOfMonth : Int,
                          hourOfDay : Int = 0,
                          minute : Int = 0,
                          second : Int = 0,
                          millisecond : Int = 0) : Calendar {
    return Calendar.getInstance(this)
        .setYear(year)
        .setMonth(month)
        .setDayOfMonth(dayOfMonth)
        .setHourOfDay(hourOfDay)
        .setMinute(minute)
        .setSecond(second)
        .setMillisecond(millisecond)
}


/**
 *
 */
fun Long.asCalendarTimeInMillis(context : Context) : Calendar {
    return this.asCalendarTimeInMillis(context.locale)
}


/**
 *
 */
fun Long.asCalendarTimeInMillis(locale : Locale) : Calendar {
    return Calendar.getInstance(locale).also {
        it.timeInMillis = this
    }
}


/**
 *
 */
fun Calendar.setYear(year : Int) : Calendar = apply {
    this.set(Calendar.YEAR, year)
}


/**
 *
 */
fun Calendar.setMonth(month : Int) : Calendar = apply {
    this.set(Calendar.MONTH, month)
}


/**
 *
 */
fun Calendar.setDayOfYear(dayOfYear : Int) : Calendar = apply {
    this.set(Calendar.DAY_OF_YEAR, dayOfYear)
}


/**
 *
 */
fun Calendar.setDayOfMonth(dayOfMonth : Int) : Calendar = apply {
    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)
}


/**
 *
 */
fun Calendar.setDayOfWeek(dayOfWeek : Int) : Calendar = apply {
    this.set(Calendar.DAY_OF_WEEK, dayOfWeek)
}


/**
 *
 */
fun Calendar.setHour(hour : Int) : Calendar = apply {
    this.set(Calendar.HOUR, hour)
}


/**
 *
 */
fun Calendar.setHourOfDay(hourOfDay : Int) : Calendar = apply {
    this.set(Calendar.HOUR_OF_DAY, hourOfDay)
}


/**
 *
 */
fun Calendar.setMinute(minute : Int) : Calendar = apply {
    this.set(Calendar.MINUTE, minute)
}


/**
 *
 */
fun Calendar.setSecond(second : Int) : Calendar = apply {
    this.set(Calendar.SECOND, second)
}


/**
 *
 */
fun Calendar.setMillisecond(millisecond : Int) : Calendar = apply {
    this.set(Calendar.MILLISECOND, millisecond)
}


/**
 *
 */
fun Calendar.previousMonth() : Calendar = apply {
    var currentYear = this.year

    if(this.month == Calendar.JANUARY) {
        this.setMonth(Calendar.DECEMBER)
        currentYear--
    } else {
        this.setMonth(this.month - 1)
    }

    this.setYear(currentYear)
}


/**
 *
 */
fun Calendar.nextMonth() : Calendar = apply {
    var currentYear = this.year

    if(this.month == Calendar.DECEMBER) {
        this.setMonth(Calendar.JANUARY)
        currentYear++
    } else {
        this.setMonth(this.month + 1)
    }

    this.setDayOfMonth(Math.min(this.dayOfMonth, this.monthLength))
    this.setYear(currentYear)
}