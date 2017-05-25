package com.samirsayegh.rxtestmarvelchars.presenter.characterDetails;

import com.samirsayegh.rxtestmarvelchars.presenter.base.BasePresenter;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.CharacterDetailsView;

/**
 * Created by yormirsamir.sayegh on 25/05/2017.
 */

public interface CharacterDetailsPresenter extends BasePresenter {

    void setView(CharacterDetailsView characterDetailsView);

    void setCharacterID(int characterID);

    void loadMoreComics();

    void loadMoreEvents();

}
