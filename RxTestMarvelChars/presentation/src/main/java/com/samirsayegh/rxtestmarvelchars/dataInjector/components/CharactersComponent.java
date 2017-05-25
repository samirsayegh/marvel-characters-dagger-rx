package com.samirsayegh.rxtestmarvelchars.dataInjector.components;

import com.samirsayegh.rxtestmarvelchars.dataInjector.PerActivity;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.ActivityModule;
import com.samirsayegh.rxtestmarvelchars.dataInjector.modules.CharactersModule;
import com.samirsayegh.rxtestmarvelchars.view.heroList.HeroListActivity;

import dagger.Component;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CharactersModule.class})
public interface CharactersComponent {
    void inject(HeroListActivity heroListActivity);
}
