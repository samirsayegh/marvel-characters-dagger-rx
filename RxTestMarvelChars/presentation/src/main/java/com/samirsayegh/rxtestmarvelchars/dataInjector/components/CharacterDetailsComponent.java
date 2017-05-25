package com.samirsayegh.rxtestmarvelchars.dataInjector.components;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ActivityModule;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.CharacterDetailsModule;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.CharacterDetailsActivity;

import dagger.Component;

/**
 * Created by yormirsamir.sayegh on 24/05/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CharacterDetailsModule.class})
public interface CharacterDetailsComponent {
    void inject(CharacterDetailsActivity characterDetailsActivity);
}
