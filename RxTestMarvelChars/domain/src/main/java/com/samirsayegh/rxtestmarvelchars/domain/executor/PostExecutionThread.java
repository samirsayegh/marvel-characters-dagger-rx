package com.samirsayegh.rxtestmarvelchars.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();

}
