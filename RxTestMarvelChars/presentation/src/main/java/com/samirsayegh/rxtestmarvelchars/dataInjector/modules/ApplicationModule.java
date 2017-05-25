package com.samirsayegh.rxtestmarvelchars.dataInjector.modules;

import android.content.Context;

import com.samirsayegh.rxtestmarvelchars.BaseApplication;
import com.samirsayegh.rxtestmarvelchars.UIThread;
import com.samirsayegh.rxtestmarvelchars.data.executor.JobExecutor;
import com.samirsayegh.rxtestmarvelchars.data.repository.CharacterDataRepository;
import com.samirsayegh.rxtestmarvelchars.domain.executor.PostExecutionThread;
import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;
import com.samirsayegh.rxtestmarvelchars.domain.repository.CharacterRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Module
public class ApplicationModule {

    private final BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    CharacterRepository provideCharacterRepository(CharacterDataRepository characterDataRepository) {
        return characterDataRepository;
    }
}
