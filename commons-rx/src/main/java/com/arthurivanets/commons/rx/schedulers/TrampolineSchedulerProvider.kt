package com.arthurivanets.commons.rx.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerProvider : SchedulerProvider {


    override val computation : Scheduler
        get() = Schedulers.trampoline()

    override val io : Scheduler
        get() = Schedulers.trampoline()

    override val ui : Scheduler
        get() = Schedulers.trampoline()


}