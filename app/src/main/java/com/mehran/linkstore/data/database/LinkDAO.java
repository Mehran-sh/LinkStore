package com.mehran.linkstore.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mehran.linkstore.data.models.Link;

import java.util.List;

/**
 * Created by mehran on 19.08.17.
 */

@Dao
public interface LinkDAO {
    @Insert
    long insertLink(Link link);

    @Update
    void updateLink(Link link);

    @Delete
    void deleteLink(Link link);

    @Query("SELECT * FROM Link")
    List<Link> loadAllLinks();
}
