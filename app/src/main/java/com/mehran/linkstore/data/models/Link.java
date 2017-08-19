package com.mehran.linkstore.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mehran on 19.08.17.
 */

@Entity
public class Link {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String url;

    private boolean isRead;

    private boolean isImportant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
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

        final Link link = (Link) obj;

        if(link.getId() != this.getId())
        {
            return false;
        }

        if(!link.getUrl().equalsIgnoreCase(this.getUrl()))
        {
            return false;
        }

        if(link.isRead() != this.isRead())
        {
            return false;
        }

        if(link.isImportant() != this.isImportant())
        {
            return false;
        }

        return true;
    }
}
