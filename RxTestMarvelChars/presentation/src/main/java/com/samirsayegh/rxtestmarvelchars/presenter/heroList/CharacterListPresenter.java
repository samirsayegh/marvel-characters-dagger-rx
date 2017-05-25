package com.samirsayegh.rxtestmarvelchars.presenter.heroList;

import com.samirsayegh.rxtestmarvelchars.presenter.base.BasePresenter;
import com.samirsayegh.rxtestmarvelchars.view.heroList.HeroListView;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public interface CharacterListPresenter extends BasePresenter {

    void setView(HeroListView heroListView);

    void loadMoreElements();
}
