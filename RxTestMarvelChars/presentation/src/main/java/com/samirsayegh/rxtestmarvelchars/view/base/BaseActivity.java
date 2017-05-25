package com.samirsayegh.rxtestmarvelchars.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.samirsayegh.rxtestmarvelchars.BaseApplication;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.ApplicationComponent;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ActivityModule;
import com.samirsayegh.rxtestmarvelchars.presenter.base.BasePresenter;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.CharacterDetailsActivity;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected static final String EXTRA_HERO = "EXTRA_HERO";

    protected int layoutId;
    protected ProgressDialog dialog;
    private BasePresenter basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        this.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    protected void setPresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void navigateWithExtra(String extra, Serializable serializable) {
        Intent intent = new Intent(this, CharacterDetailsActivity.class);
        intent.putExtra(extra, serializable);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (basePresenter != null)
            basePresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (basePresenter != null)
            basePresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null)
            basePresenter.destroy();
    }

    @Override
    public void showLoading() {
        dialog = ProgressDialog.show(BaseActivity.this, "", "Loading. Please wait...", true);
    }

    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing())
            dialog.cancel();
        dialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return this;
    }
}
