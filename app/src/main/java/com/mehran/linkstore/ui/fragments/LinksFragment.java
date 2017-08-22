package com.mehran.linkstore.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.github.clans.fab.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.database.DatabaseManagement;
import com.mehran.linkstore.data.models.Link;
import com.mehran.linkstore.ui.adapters.CustomListAdapter;
import com.mehran.linkstore.ui.adapters.viewholders.LinkItemViewHolder;
import com.mehran.linkstore.ui.dialogs.LinkDialog;
import com.mehran.linkstore.ui.tasks.SaveLinkTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehran on 20.08.17.
 */

public class LinksFragment extends Fragment implements View.OnClickListener, LinkDialog.ILinkDialogListener {

    private static final String TAG = LinksFragment.class.getSimpleName();
    private static final int REQUEST_ADD_LINK = 1;

    @BindView(R.id.lvLinks)
    ListView lvLinks;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

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

        floatingActionButton.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab:
                showLinkDialog(null);
                break;
        }
    }

    private void showLinkDialog(Link link)
    {
        LinkDialog linkDialog = LinkDialog.getInstance(link);
        linkDialog.setTargetFragment(this, REQUEST_ADD_LINK);
        linkDialog.show(getFragmentManager(), LinkDialog.class.getSimpleName());
    }

    @Override
    public void onSaveLink(Link link) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_ADD_LINK:
                if(resultCode == Activity.RESULT_OK)
                {
                    Link link = data.getExtras().getParcelable(Link.KEY);
                    saveLink(link);
                }
                break;
        }
    }

    private void saveLink(Link link)
    {
        new SaveLinkTask(DatabaseManagement.getInstance(getActivity().getApplicationContext()).getDb().linkDAO())
                .execute(link);
    }
}
