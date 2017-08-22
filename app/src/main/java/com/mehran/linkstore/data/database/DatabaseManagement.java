package com.mehran.linkstore.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by mehran on 19.08.17.
 */

public class DatabaseManagement {

    private static final String DATABASE_NAME = "linkstoredb";

    LinkStoreDB db = null;

    private static DatabaseManagement instance = null;

    public static DatabaseManagement getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseManagement(context);
        }
        return instance;
    }

    private DatabaseManagement(Context context)
    {
        this.db = Room.databaseBuilder(context,
                LinkStoreDB.class, DATABASE_NAME).build();
    }

    public LinkStoreDB getDb()
    {
        return db;
    }
}
