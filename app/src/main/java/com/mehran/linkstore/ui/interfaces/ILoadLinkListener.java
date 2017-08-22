package com.mehran.linkstore.ui.interfaces;

import com.mehran.linkstore.data.models.Link;

import java.util.List;

/**
 * Created by mehran on 22.08.17.
 */

public interface ILoadLinkListener {
    void onLinksLoaded(List<Link> links);
}
