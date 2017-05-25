package com.samirsayegh.rxtestmarvelchars.data.repository;

import com.samirsayegh.rxtestmarvelchars.data.repository.datasource.CharacterDataStoreFactory;
import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.repository.CharacterRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */
@Singleton
public class CharacterDataRepository implements CharacterRepository {

    private final CharacterDataStoreFactory characterDataStoreFactory;

    @Inject
    CharacterDataRepository(CharacterDataStoreFactory characterDataStoreFactory) {
        this.characterDataStoreFactory = characterDataStoreFactory;
    }

    @Override
    public Observable<OffsetList<Hero>> heroes() {
        return characterDataStoreFactory.getCharacters().heroList();
    }

    @Override
    public Observable<OffsetList<Hero>> heroes(int offset) {
        return characterDataStoreFactory.getCharacters().heroList(offset);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId) {
        return characterDataStoreFactory.getCharacters().getComics(characterId);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId, int offset) {
        return characterDataStoreFactory.getCharacters().getComics(characterId, offset);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId) {
        return characterDataStoreFactory.getCharacters().getEvents(characterId);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId, int offset) {
        return characterDataStoreFactory.getCharacters().getEvents(characterId, offset);
    }
}
