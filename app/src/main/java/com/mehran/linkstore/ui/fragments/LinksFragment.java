package com.mehran.linkstore.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.github.clans.fab.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.database.DatabaseManagement;
import com.mehran.linkstore.data.models.Link;
import com.mehran.linkstore.ui.adapters.CustomListAdapter;
import com.mehran.linkstore.ui.adapters.viewholders.LinkItemViewHolder;
import com.mehran.linkstore.ui.dialogs.LinkDialog;
import com.mehran.linkstore.ui.interfaces.ILoadLinkListener;
import com.mehran.linkstore.ui.tasks.LoadLinksTask;
import com.mehran.linkstore.ui.tasks.SaveLinkTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehran on 20.08.17.
 */

public class LinksFragment extends Fragment implements View.OnClickListener,
    ListView.OnItemClickListener
{

    private static final String TAG = LinksFragment.class.getSimpleName();
    private static final int REQUEST_ADD_LINK = 1;

    @BindView(R.id.lvLinks)
    ListView lvLinks;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    CustomListAdapter<Link, LinkItemViewHolder> linksAdapter ;

    String urlToShare = null;

    public static LinksFragment getInstance(String url)
    {
        LinksFragment fragment = new LinksFragment();
        if(url != null)
        {
            Bundle args = new Bundle();
            args.putString("url", url);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        ButterKnife.bind(this, view);
        initUi();
        showDialogIfUrlExists();
        return view;
    }

    private void showDialogIfUrlExists()
    {
        if(urlToShare != null)
        {
            Link link = new Link();
            link.setUrl(urlToShare);
            showLinkDialog(link);
        }
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        if(args.containsKey("url"))
        {
            urlToShare = args.getString("url");
        }
    }

    private void initUi()
    {
        floatingActionButton.setOnClickListener(this);
        loadLinks();
    }

    private void buildLinksList(List<Link> links)
    {
        if(linksAdapter == null) {
            linksAdapter = new CustomListAdapter<>(links, LinkItemViewHolder.class, R.layout.listitem_link);
            lvLinks.setAdapter(linksAdapter);
            lvLinks.setOnItemClickListener(this);
        }
        else
        {
            linksAdapter.setData(links);
            linksAdapter.notifyDataSetChanged();
        }
    }

    private void loadLinks()
    {
        new LoadLinksTask(DatabaseManagement.getInstance(getActivity().getApplicationContext())
                .getDb().linkDAO(), new ILoadLinkListener() {
            @Override
            public void onLinksLoaded(List<Link> links) {
                buildLinksList(links);
            }
        }).execute();
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

        loadLinks();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Link link = linksAdapter.getItem(position);
        Log.d(TAG, "Link selected: " + link.toString());

        // TODO: 22.08.17 set as read
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getUrl()));
        startActivity(browserIntent);
        
    }
}
