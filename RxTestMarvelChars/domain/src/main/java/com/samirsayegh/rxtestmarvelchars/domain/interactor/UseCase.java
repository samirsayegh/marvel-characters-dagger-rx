package com.samirsayegh.rxtestmarvelchars.domain.interactor;

import com.samirsayegh.rxtestmarvelchars.domain.executor.PostExecutionThread;
import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public abstract class UseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public void addCompositeDisposable(Disposable disposable) {
        if (disposable != null) {
            disposables.add(disposable);
        }
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    public void execute(DisposableObserver<T> observer, Params params) {
        if (observer != null) {
            final Observable<T> observable = buildObservable(params)
                    .subscribeOn(Schedulers.from(threadExecutor))
                    .observeOn(postExecutionThread.getScheduler());
            addCompositeDisposable(observable.subscribeWith(observer));
        }
    }

    protected abstract Observable<T> buildObservable(Params params);
}
