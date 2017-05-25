package com.samirsayegh.rxtestmarvelchars.presenter.base;

import com.samirsayegh.rxtestmarvelchars.view.base.BaseView;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public abstract class BasePresenterImpl implements BasePresenter {

    protected static final int OFFSET_STEP = 20;

    private BaseView baseView;

    protected void showViewLoading() {
        this.baseView.showLoading();
    }

    protected void hideViewLoading() {
        this.baseView.hideLoading();
    }

    protected void showErrorMessage(String error) {
        this.baseView.showError(error);
    }

    public void setView(BaseView baseView) {
        this.baseView = baseView;
    }
}
