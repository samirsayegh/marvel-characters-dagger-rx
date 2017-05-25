package com.samirsayegh.rxtestmarvelchars.domain.interactor;

import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.executor.PostExecutionThread;
import com.samirsayegh.rxtestmarvelchars.domain.executor.ThreadExecutor;
import com.samirsayegh.rxtestmarvelchars.domain.repository.CharacterRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public class GetCharacterList extends UseCase<OffsetList<Hero>, Integer> {

    private final CharacterRepository characterRepository;

    @Inject
    GetCharacterList(CharacterRepository characterRepository, ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.characterRepository = characterRepository;
    }

    @Override
    protected Observable<OffsetList<Hero>> buildObservable(Integer offset) {
        if (offset <= 0)
            return characterRepository.heroes();
        return characterRepository.heroes(offset);
    }
}
