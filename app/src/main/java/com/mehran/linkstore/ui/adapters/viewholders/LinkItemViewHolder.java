package com.mehran.linkstore.ui.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.models.Link;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehran on 20.08.17.
 */

public class LinkItemViewHolder extends AbstractViewHolder<Link> {

    @BindView(R.id.lblUrl)
    TextView lblUrl;

    public LinkItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void setData(Link link) {
        lblUrl.setText(link.getUrl());
    }
}
