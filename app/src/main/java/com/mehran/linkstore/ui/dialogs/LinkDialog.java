package com.mehran.linkstore.ui.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.models.Link;

import butterknife.ButterKnife;

/**
 * Created by mehran on 21.08.17.
 */

public class LinkDialog extends DialogFragment {


    public static LinkDialog getInstance(Link link)
    {
        LinkDialog linkDialog = new LinkDialog();

        return linkDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_link, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi()
    {

    }
}
