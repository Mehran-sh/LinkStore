package com.mehran.linkstore.data.database;

/**
 * Created by mehran on 19.08.17.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mehran.linkstore.data.models.Label;

@Dao
public interface LabelDAO {
    @Insert
    void insertLabel(Label label);

    @Update
    void update(Label label);

    @Delete
    void delete(Label label);

    @Query("SELECT * FROM Label")
    Label[] loadAllLabels();
}
