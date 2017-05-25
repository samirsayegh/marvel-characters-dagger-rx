package com.samirsayegh.rxtestmarvelchars.domain.interactor;

import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.HeroIdOffset;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.executor.PostExecutionThread;
import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;
import com.samirsayegh.rxtestmarvelchars.domain.repository.CharacterRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public class GetComicList extends UseCase<OffsetList<BaseContent>, HeroIdOffset> {

    private final CharacterRepository characterRepository;

    @Inject
    GetComicList(CharacterRepository characterRepository, ThreadExecutor threadExecutor,
                 PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.characterRepository = characterRepository;
    }

    @Override
    protected Observable<OffsetList<BaseContent>> buildObservable(HeroIdOffset heroIdOffset) {
        if (heroIdOffset.isComic()) {
            if (heroIdOffset.getOffset() <= 0)
                return characterRepository.getComics(heroIdOffset.getHeroId());
            return characterRepository.getComics(heroIdOffset.getOffset(), heroIdOffset.getHeroId());
        } else {
            if (heroIdOffset.getOffset() <= 0)
                return characterRepository.getEvents(heroIdOffset.getHeroId());
            return characterRepository.getEvents(heroIdOffset.getOffset(), heroIdOffset.getHeroId());
        }
    }
}
