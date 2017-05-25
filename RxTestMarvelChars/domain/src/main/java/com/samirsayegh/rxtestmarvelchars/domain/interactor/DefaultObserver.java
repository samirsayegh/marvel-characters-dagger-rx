package com.samirsayegh.rxtestmarvelchars.domain.interactor;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
