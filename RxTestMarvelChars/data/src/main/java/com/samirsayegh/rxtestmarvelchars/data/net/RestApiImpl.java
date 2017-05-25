package com.samirsayegh.rxtestmarvelchars.data.net;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samirsayegh.rxtestmarvelchars.data.dto.HeroDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.TitleDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.BaseDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.mapper.HeroMapper;
import com.samirsayegh.rxtestmarvelchars.data.dto.mapper.TitleMapper;
import com.samirsayegh.rxtestmarvelchars.data.utils.NetworkUtils;
import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yormirsamir.sayegh on 17/05/2017.
 */

public class RestApiImpl extends BaseService implements RestApi {

    private final Context context;

    public RestApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<OffsetList<Hero>> getCharacters() {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getCharacters().enqueue(getCallbackHeroes(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @Override
    public Observable<OffsetList<Hero>> getCharacters(int offset) {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getCharacters(offset).enqueue(getCallbackHeroes(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId) {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getComics(characterId).enqueue(getCallbackComicEvent(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @Override
    public Observable<OffsetList<BaseContent>> getComics(int characterId, int offset) {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getComics(characterId, offset).enqueue(getCallbackComicEvent(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId) {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getEvents(characterId).enqueue(getCallbackComicEvent(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @Override
    public Observable<OffsetList<BaseContent>> getEvents(int characterId, int offset) {
        return Observable.create(emitter -> {
            if (NetworkUtils.isNetworkConnection(context)) {
                api.getEvents(characterId, offset).enqueue(getCallbackComicEvent(emitter));
            } else {
                emitter.onError(new Exception("Network connection exception Utils"));
            }
        });
    }

    @NonNull
    private Callback<BaseDTO<List<HeroDTO>>> getCallbackHeroes(final ObservableEmitter<OffsetList<Hero>> emitter) {
        return new Callback<BaseDTO<List<HeroDTO>>>() {
            @Override
            public void onResponse(Call<BaseDTO<List<HeroDTO>>> call, Response<BaseDTO<List<HeroDTO>>> response) {
                if (response != null && response.body() != null) {
                    OffsetList<Hero> heroes = HeroMapper.toHeroList(response.body());
                    emitter.onNext(heroes);
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("Network connection exception"));
                }
            }

            @Override
            public void onFailure(Call<BaseDTO<List<HeroDTO>>> call, Throwable t) {
                emitter.onError(new Exception("Network connection exception " + t.getMessage()));
            }
        };
    }

    @NonNull
    private Callback<BaseDTO<List<TitleDTO>>> getCallbackComicEvent(ObservableEmitter<OffsetList<BaseContent>> emitter) {
        return new Callback<BaseDTO<List<TitleDTO>>>() {
            @Override
            public void onResponse(Call<BaseDTO<List<TitleDTO>>> call, Response<BaseDTO<List<TitleDTO>>> response) {
                if (response != null && response.body() != null) {
                    OffsetList<BaseContent> baseContents = TitleMapper.toOffsetList(response.body());
                    emitter.onNext(baseContents);
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("Network connection exception"));
                }
            }

            @Override
            public void onFailure(Call<BaseDTO<List<TitleDTO>>> call, Throwable t) {
                emitter.onError(new Exception("Network connection exception " + t.getMessage()));
            }
        };
    }
}
