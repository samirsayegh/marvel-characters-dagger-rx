package com.samirsayegh.rxtestmarvelchars.view.base;

import android.content.Context;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    Context context();
}
