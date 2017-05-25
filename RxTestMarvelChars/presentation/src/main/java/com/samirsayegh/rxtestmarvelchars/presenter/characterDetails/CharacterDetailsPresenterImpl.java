package com.samirsayegh.rxtestmarvelchars.presenter.characterDetails;

import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.HeroIdOffset;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.interactor.DefaultObserver;
import com.samirsayegh.rxtestmarvelchars.domain.interactor.GetComicList;
import com.samirsayegh.rxtestmarvelchars.presenter.base.BasePresenterImpl;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.CharacterDetailsView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by yormirsamir.sayegh on 25/05/2017.
 */

public class CharacterDetailsPresenterImpl extends BasePresenterImpl implements CharacterDetailsPresenter {

    private final GetComicList getComicList;

    private CharacterDetailsView view;

    private int currentOffsetComics;
    private int totalComics;
    private int currentOffsetEvents;
    private int totalEvents;
    private boolean isLoadingComics;
    private boolean isLoadingEvents;
    private int characterId;

    @Inject
    public CharacterDetailsPresenterImpl(GetComicList getComicList) {
        this.getComicList = getComicList;
    }


    private void getEvents() {
        isLoadingEvents = true;
        getComicList.execute(new ComicListObserver(false), new HeroIdOffset.HeroIdOffsetBuilder()
                .offset(0)
                .heroId(characterId)
                .isComic(false)
                .build());
    }

    private void getComics() {
        isLoadingComics = true;
        getComicList.execute(new ComicListObserver(true), new HeroIdOffset.HeroIdOffsetBuilder()
                .offset(0)
                .heroId(characterId)
                .isComic(true)
                .build());
    }

    private void hideWaiting() {
        if (!isLoadingEvents && !isLoadingComics) {
            hideViewLoading();
        }
    }

    private void saveListPosition(OffsetList<BaseContent> list, boolean isComic) {
        if (isComic) {
            currentOffsetComics = list.getOffset();
            totalComics = list.getTotal();
        } else {
            currentOffsetEvents = list.getOffset();
            totalEvents = list.getTotal();
        }
    }

    private void showHeroList(OffsetList<BaseContent> baseContentList, boolean isComic) {
        saveListPosition(baseContentList, isComic);
        if (baseContentList != null) {
            if (isComic) {
                if (currentOffsetComics == 0) {
                    view.comicsLoaded(baseContentList.getList(), totalComics);
                } else
                    view.comicsUpdated(baseContentList.getList());
            } else {
                if (currentOffsetEvents == 0) {
                    view.eventsLoaded(baseContentList.getList(), totalEvents);
                } else
                    view.eventsUpdated(baseContentList.getList());
            }
        }
    }

    @Override
    public void initialize() {
        showViewLoading();
        getComics();
        getEvents();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getComicList.dispose();
    }

    @Override
    public void setView(CharacterDetailsView characterDetailsView) {
        super.setView(characterDetailsView);
        view = characterDetailsView;
    }

    @Override
    public void setCharacterID(int characterID) {
        this.characterId = characterID;
    }

    @Override
    public void loadMoreComics() {
        if (!view.isLoadingMoreComics()) {
            if (currentOffsetComics + OFFSET_STEP < totalComics) {
                getComicList.execute(new ComicListObserver(true), new HeroIdOffset.HeroIdOffsetBuilder()
                        .offset(currentOffsetComics + OFFSET_STEP)
                        .heroId(characterId)
                        .isComic(true)
                        .build());
            }
        }

    }

    @Override
    public void loadMoreEvents() {
        if (!view.isLoadingMoreEvents()) {
            if (currentOffsetEvents + OFFSET_STEP < totalEvents) {
                getComicList.execute(new ComicListObserver(true), new HeroIdOffset.HeroIdOffsetBuilder()
                        .offset(currentOffsetEvents + OFFSET_STEP)
                        .heroId(characterId)
                        .isComic(false)
                        .build());
            }
        }
    }

    private final class ComicListObserver extends DefaultObserver<OffsetList<BaseContent>> {

        private final boolean isComic;

        public ComicListObserver(boolean isComic) {
            this.isComic = isComic;
        }

        private void setLoadingFalse() {
            if (isComic) isLoadingComics = false;
            else isLoadingEvents = false;
        }

        @Override
        public void onNext(@NonNull OffsetList<BaseContent> baseContentOffsetList) {
            setLoadingFalse();
            showHeroList(baseContentOffsetList, isComic);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            setLoadingFalse();
            hideWaiting();
            showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            hideWaiting();
        }
    }
}
