package com.mehran.linkstore.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.mehran.linkstore.data.models.Label;
import com.mehran.linkstore.data.models.Link;

/**
 * Created by mehran on 19.08.17.
 */

@Dao
public interface LinkLabelDAO {
    @Query("SELECT * FROM Link l JOIN LinkLabel ll ON l.id = ll.linkId WHERE ll.labelId = :labelId")
    Link[] getLinksByLabelId(int labelId);

    @Query("SELECT * FROM Label l JOIN LinkLabel ll ON l.id = ll.labelId WHERE ll.linkId = :linkId")
    Label[] getLabelsByLinkId(int linkId);
}
