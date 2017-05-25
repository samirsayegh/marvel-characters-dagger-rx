package com.samirsayegh.rxtestmarvelchars.dataInjector.modules;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }
}
