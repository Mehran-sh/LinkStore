package com.mehran.linkstore.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mehran.linkstore.data.models.Label;
import com.mehran.linkstore.data.models.Link;
import com.mehran.linkstore.data.models.LinkLabel;

/**
 * Created by mehran on 19.08.17.
 */

@Database(
        entities = {
            Link.class,
            Label.class,
            LinkLabel.class
        },
        version = 1
)
public abstract class LinkStoreDB extends RoomDatabase {
    public abstract LinkDAO linkDAO();
    public abstract LabelDAO labelDAO();
    public abstract LinkLabelDAO linkLabelDAO();
}
