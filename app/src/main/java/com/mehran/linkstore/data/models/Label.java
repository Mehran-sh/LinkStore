package com.mehran.linkstore.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mehran on 19.08.17.
 */
@Entity
public class Label {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
        {
            return false;
        }

        if(getClass() != obj.getClass())
        {
            return false;
        }

        final Label targetLabel = (Label) obj;

        if(targetLabel.getId() != this.getId())
        {
            return false;
        }

        if(!targetLabel.getTitle().equalsIgnoreCase(this.getTitle()))
        {
            return false;
        }

        if(!targetLabel.getColor().equalsIgnoreCase(this.getColor()))
        {
            return false;
        }

        return true;
    }
}
