package com.samirsayegh.rxtestmarvelchars.data.repository.datasource;

import com.samirsayegh.rxtestmarvelchars.data.net.RestApi;
import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public class CloudCharacterDataStore implements CharacterDataStore {

    private final RestApi restApi;

    public CloudCharacterDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<OffsetList<Hero>> heroList() {
        return restApi.getCharacters();
    }

    @Override
    public Observable<OffsetList<Hero>> heroList(int offset) {
        return restApi.getCharacters(offset);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId) {
        return restApi.getComics(characterId);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId, int offset) {
        return restApi.getComics(characterId, offset);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId) {
        return restApi.getEvents(characterId);
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId, int offset) {
        return restApi.getEvents(characterId, offset);
    }
}
