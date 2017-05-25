package com.samirsayegh.rxtestmarvelchars.view.characterDetails;

import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.view.base.BaseView;

import java.util.List;

/**
 * Created by yormirsamir.sayegh on 24/05/2017.
 */

public interface CharacterDetailsView extends BaseView {

    void comicsLoaded(List<BaseContent> list, int totalComics);

    void comicsUpdated(List<BaseContent> list);

    void eventsLoaded(List<BaseContent> list, int totalEvents);

    void eventsUpdated(List<BaseContent> list);

    boolean isLoadingMoreComics();

    void stopLoadingComics();

    boolean isLoadingMoreEvents();

    void stopLoadingEvents();
}
