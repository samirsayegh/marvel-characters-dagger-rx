package com.samirsayegh.rxtestmarvelchars.dataInjector.components;

import android.content.Context;

import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ApplicationModule;
import com.samirsayegh.rxtestmarvelchars.domain.executor.PostExecutionThread;
import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;
import com.samirsayegh.rxtestmarvelchars.domain.repository.CharacterRepository;
import com.samirsayegh.rxtestmarvelchars.view.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    CharacterRepository characterRepository();
}
