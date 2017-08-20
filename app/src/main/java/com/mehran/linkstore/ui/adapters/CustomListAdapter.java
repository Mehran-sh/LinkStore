package com.mehran.linkstore.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mehran.linkstore.ui.adapters.viewholders.AbstractViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by mehran on 20.08.17.
 */

public class CustomListAdapter<T, E extends AbstractViewHolder> extends BaseAdapter {
    protected List<T> data;
    Class<E> viewHolder;
    protected int viewId;

    public CustomListAdapter(List<T> data, Class<E> viewHolder, int viewId)
    {
        this.data = data;
        this.viewHolder = viewHolder;
        this.viewId = viewId;

    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbstractViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(viewId, parent, false);
            try {
                vh = viewHolder.getDeclaredConstructor(View.class).newInstance(convertView);
                convertView.setTag(vh);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            vh = (AbstractViewHolder) convertView.getTag();
        }
        if(vh != null)
        {
            vh.setData(data.get(position));
        }


        return convertView;
    }
}
