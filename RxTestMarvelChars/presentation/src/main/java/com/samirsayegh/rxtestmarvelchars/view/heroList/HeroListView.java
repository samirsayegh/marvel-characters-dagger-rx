package com.samirsayegh.rxtestmarvelchars.view.heroList;

import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.view.base.BaseView;

import java.util.List;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */

public interface HeroListView extends BaseView {

    void listLoaded(List<Hero> heroList);

    void listUpdated(List<Hero> heroList);

    boolean isLoadingMoreItems();

    void stopLoading();
}
