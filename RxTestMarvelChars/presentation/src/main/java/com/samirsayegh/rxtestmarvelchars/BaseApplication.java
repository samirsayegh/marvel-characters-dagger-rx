package com.samirsayegh.rxtestmarvelchars;

import android.app.Application;

import com.samirsayegh.rxtestmarvelchars.dataInjector.components.ApplicationComponent;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.DaggerApplicationComponent;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ApplicationModule;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
