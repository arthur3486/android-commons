package com.arthurivanets.commons.rx.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {

    val computation : Scheduler
    val io : Scheduler
    val ui : Scheduler

}