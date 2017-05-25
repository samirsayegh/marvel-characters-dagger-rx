package com.samirsayegh.rxtestmarvelchars.dataInjector.modules;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;
import com.samirsayegh.rxtestmarvelchars.presenter.characterDetails.CharacterDetailsPresenter;
import com.samirsayegh.rxtestmarvelchars.presenter.characterDetails.CharacterDetailsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yormirsamir.sayegh on 24/05/2017.
 */
@Module
public class CharacterDetailsModule {

    @Provides
    @PerActivity
    CharacterDetailsPresenter provideCharacterDetailsPresenter(CharacterDetailsPresenterImpl characterListPresenter) {
        return characterListPresenter;
    }
}
