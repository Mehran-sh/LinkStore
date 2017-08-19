package com.mehran.linkstore.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by mehran on 19.08.17.
 */
@Entity(
        foreignKeys = {
            @ForeignKey(entity = Link.class, parentColumns = "id", childColumns = "linkId"),
            @ForeignKey(entity = Label.class, parentColumns = "id", childColumns = "labelId")
        },
        primaryKeys = {
                "linkId",
                "labelId"
        })
public class LinkLabel {
    private int linkId;
    private int labelId;

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}
