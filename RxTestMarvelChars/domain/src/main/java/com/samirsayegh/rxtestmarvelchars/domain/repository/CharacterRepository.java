package com.samirsayegh.rxtestmarvelchars.domain.repository;

import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public interface CharacterRepository {

    Observable<OffsetList<Hero>> heroes();

    Observable<OffsetList<Hero>> heroes(int offset);

    Observable<OffsetList<BaseContent>> getComics(int characterId);

    Observable<OffsetList<BaseContent>> getComics(int characterId, int offset);

    Observable<OffsetList<BaseContent>> getEvents(int characterId);

    Observable<OffsetList<BaseContent>> getEvents(int characterId, int offset);
}
