package com.arthurivanets.commons.rx.schedulers

import io.reactivex.Scheduler

class TestSchedulerProvider(scheduler : Scheduler) : SchedulerProvider {


    override val computation = scheduler
    override val io = scheduler
    override val ui = scheduler


}