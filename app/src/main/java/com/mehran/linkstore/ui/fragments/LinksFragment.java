package com.mehran.linkstore.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.models.Link;
import com.mehran.linkstore.ui.adapters.CustomListAdapter;
import com.mehran.linkstore.ui.adapters.viewholders.LinkItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehran on 20.08.17.
 */

public class LinksFragment extends Fragment {

    @BindView(R.id.lvLinks)
    ListView lvLinks;
    CustomListAdapter<Link, LinkItemViewHolder> linksAdapter;

    public static LinksFragment getInstance()
    {
        LinksFragment fragment = new LinksFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi()
    {
        linksAdapter = new CustomListAdapter<>(getLinks(), LinkItemViewHolder.class, R.layout.listitem_link);
        lvLinks.setAdapter(linksAdapter);
    }

    private List<Link> getLinks()
    {
        Link l1 = new Link();
        l1.setUrl("link 1");

        Link l2 = new Link();
        l2.setUrl("link 2");

        List<Link> links = new ArrayList<>();
        links.add(l1);
        links.add(l2);

        return links;
    }
}
