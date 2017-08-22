package com.mehran.linkstore.ui.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.mehran.linkstore.data.database.LinkDAO;
import com.mehran.linkstore.data.models.Link;

/**
 * Created by mehran on 22.08.17.
 */

public class SaveLinkTask extends AsyncTask<Link, Void, Void>
{
    private static final String TAG = SaveLinkTask.class.getSimpleName();

    private LinkDAO linkDAO;

    public SaveLinkTask(LinkDAO linkDAO)
    {
        this.linkDAO = linkDAO;
    }

    @Override
    protected Void doInBackground(Link... links) {
        Link link = links[0];
        if(link.getId() > 0)
        {
            linkDAO.updateLink(link);
            Log.d(TAG, "Link updated: " + link.toString());
        }
        else
        {
            linkDAO.insertLink(link);
            Log.d(TAG, "Link inserted: " + link.toString());
        }
        return null;
    }
}