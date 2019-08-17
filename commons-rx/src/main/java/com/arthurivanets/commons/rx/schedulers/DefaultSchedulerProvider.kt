package com.arthurivanets.commons.rx.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultSchedulerProvider : SchedulerProvider {


    override val computation : Scheduler
        get() = Schedulers.computation()

    override val io : Scheduler
        get() = Schedulers.io()

    override val ui : Scheduler
        get() = AndroidSchedulers.mainThread()


}