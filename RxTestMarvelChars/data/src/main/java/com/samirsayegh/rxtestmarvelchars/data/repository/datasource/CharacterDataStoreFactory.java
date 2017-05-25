package com.samirsayegh.rxtestmarvelchars.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samirsayegh.rxtestmarvelchars.data.net.RestApi;
import com.samirsayegh.rxtestmarvelchars.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */
@Singleton
public class CharacterDataStoreFactory {

    private final Context context;

    @Inject
    CharacterDataStoreFactory(@NonNull Context context) {
        this.context = context;
    }

    public CharacterDataStore getCharacters() {
        RestApi restApi = new RestApiImpl(context);
        return new CloudCharacterDataStore(restApi);
    }

}
