package com.mehran.linkstore.ui.adapters.viewholders;

import android.content.Context;
import android.view.View;

/**
 * Created by mehran on 20.08.17.
 */

public abstract class AbstractViewHolder<T> {
    public AbstractViewHolder(View view){};

    public abstract void setData(T obj);
}