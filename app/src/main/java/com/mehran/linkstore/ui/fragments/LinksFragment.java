package com.mehran.linkstore.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mehran.linkstore.R;

/**
 * Created by mehran on 20.08.17.
 */

public class LinksFragment extends Fragment {

    public static LinksFragment getInstance()
    {
        LinksFragment fragment = new LinksFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);

        return view;
    }
}
