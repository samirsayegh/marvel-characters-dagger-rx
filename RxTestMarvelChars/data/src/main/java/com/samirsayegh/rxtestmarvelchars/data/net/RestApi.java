package com.samirsayegh.rxtestmarvelchars.data.net;

import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public interface RestApi {

    Observable<OffsetList<Hero>> getCharacters();

    Observable<OffsetList<Hero>> getCharacters(int offset);

    Observable<OffsetList<BaseContent>> getComics(int characterId);

    Observable<OffsetList<BaseContent>> getComics(int characterId, int offset);

    Observable<OffsetList<BaseContent>> getEvents(int characterId);

    Observable<OffsetList<BaseContent>> getEvents(int characterId, int offset);

}
