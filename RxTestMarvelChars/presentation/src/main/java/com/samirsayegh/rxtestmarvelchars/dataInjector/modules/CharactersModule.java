package com.samirsayegh.rxtestmarvelchars.dataInjector.modules;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;
import com.samirsayegh.rxtestmarvelchars.presenter.heroList.CharacterListPresenter;
import com.samirsayegh.rxtestmarvelchars.presenter.heroList.CharacterListPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Module
public class CharactersModule {

    public CharactersModule() {
    }

    @Provides
    @PerActivity
    CharacterListPresenter provideCharacterListPresenter(CharacterListPresenterImpl characterListPresenter) {
        return characterListPresenter;
    }
}
