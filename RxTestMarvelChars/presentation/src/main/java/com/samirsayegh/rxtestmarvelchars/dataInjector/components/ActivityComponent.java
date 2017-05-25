package com.samirsayegh.rxtestmarvelchars.dataInjector.components;

import android.app.Activity;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();

}
