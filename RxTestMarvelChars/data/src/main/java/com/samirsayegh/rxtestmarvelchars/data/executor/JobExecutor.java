package com.samirsayegh.rxtestmarvelchars.data.executor;

import android.support.annotation.NonNull;

import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }
}
