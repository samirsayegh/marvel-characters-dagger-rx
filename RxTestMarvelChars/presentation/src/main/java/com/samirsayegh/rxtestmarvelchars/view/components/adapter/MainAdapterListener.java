package com.samirsayegh.rxtestmarvelchars.view.components.adapter;

import android.view.View;

/**
 * Created by yormirsamir.sayegh on 28/04/2017.
 */

public interface MainAdapterListener extends View.OnClickListener {

    void loadMoreElements();

    @Override
    void onClick(View v);
}
