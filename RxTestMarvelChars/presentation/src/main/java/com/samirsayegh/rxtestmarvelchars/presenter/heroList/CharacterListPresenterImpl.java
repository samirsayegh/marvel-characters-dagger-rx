package com.samirsayegh.rxtestmarvelchars.presenter.heroList;

import android.support.annotation.NonNull;

import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.interactor.DefaultObserver;
import com.samirsayegh.rxtestmarvelchars.domain.interactor.GetCharacterList;
import com.samirsayegh.rxtestmarvelchars.presenter.base.BasePresenterImpl;
import com.samirsayegh.rxtestmarvelchars.view.heroList.HeroListView;

import javax.inject.Inject;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public class CharacterListPresenterImpl extends BasePresenterImpl implements CharacterListPresenter {

    private final GetCharacterList getCharacterList;

    private int currentOffset;
    private int total;

    private HeroListView view;

    @Inject
    CharacterListPresenterImpl(GetCharacterList getCharacterList) {
        this.getCharacterList = getCharacterList;
    }

    private void getCharacterList() {
        getCharacterList.execute(new CharacterListObserver(), 0);
    }

    private void showHeroList(OffsetList<Hero> heroOffsetList) {
        saveListPosition(heroOffsetList);
        if (heroOffsetList != null) {
            if (currentOffset == 0)
                view.listLoaded(heroOffsetList.getList());
            else
                view.listUpdated(heroOffsetList.getList());
        }
    }

    private void saveListPosition(OffsetList<Hero> heroList) {
        currentOffset = heroList.getOffset();
        total = heroList.getTotal();
    }

    @Override
    public void initialize() {
        showViewLoading();
        getCharacterList();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getCharacterList.dispose();
    }

    @Override
    public void setView(@NonNull HeroListView heroListView) {
        super.setView(heroListView);
        this.view = heroListView;
    }

    @Override
    public void loadMoreElements() {
        if (!view.isLoadingMoreItems()) {
            if (currentOffset + OFFSET_STEP < total) {
                //if (startingWith.isEmpty())
                getCharacterList.execute(new CharacterListObserver(), currentOffset + OFFSET_STEP);
                //else
                //    characterService.getCharactersStartingWith(startingWith, currentOffset + OFFSET_STEP);
            }
        }
    }

    private final class CharacterListObserver extends DefaultObserver<OffsetList<Hero>> {
        @Override
        public void onNext(@NonNull OffsetList<Hero> heroOffsetList) {
            showHeroList(heroOffsetList);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            hideViewLoading();
            showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            hideViewLoading();
        }
    }
}
