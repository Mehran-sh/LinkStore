package com.mehran.linkstore;

import com.mehran.linkstore.data.models.Label;
import com.mehran.linkstore.data.models.Link;

/**
 * Created by mehran on 19.08.17.
 */

public class TestUtil {
    public static Link createLink(String url, boolean isRead, boolean isImportant)
    {
        Link link = new Link();
        link.setUrl(url);
        link.setRead(isRead);
        link.setImportant(isImportant);

        return link;
    }

    public static Label createLabel(String title, String color)
    {
        Label label = new Label();
        label.setTitle(title);
        label.setColor(color);
        return label;
    }
}
