package com.mehran.linkstore.ui.tasks;

import android.os.AsyncTask;

import com.mehran.linkstore.data.database.LinkDAO;
import com.mehran.linkstore.data.models.Link;
import com.mehran.linkstore.ui.interfaces.ILoadLinkListener;

import java.util.List;

/**
 * Created by mehran on 22.08.17.
 */

public class LoadLinksTask extends AsyncTask<Void, Void, List<Link>> {

    private LinkDAO linkDAO;
    private ILoadLinkListener loadLinkListener;

    public LoadLinksTask(LinkDAO linkDAO, ILoadLinkListener listener)
    {
        this.linkDAO = linkDAO;
        this.loadLinkListener = listener;
    }

    @Override
    protected List<Link> doInBackground(Void... voids) {
        return this.linkDAO.loadAllLinks();
    }

    @Override
    protected void onPostExecute(List<Link> links) {
        super.onPostExecute(links);
        if(this.loadLinkListener != null)
        {
            this.loadLinkListener.onLinksLoaded(links);
        }
    }
}
