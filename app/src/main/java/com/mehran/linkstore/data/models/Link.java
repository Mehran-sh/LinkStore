package com.mehran.linkstore.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mehran on 19.08.17.
 */

@Entity
public class Link implements Parcelable {
    public static final String KEY = Link.class.getSimpleName();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeLong(this.id);
        out.writeString(this.url);
        out.writeByte((byte)(this.isRead ? 1 : 0));
        out.writeByte((byte)(this.isImportant ? 1 : 0));
    }

    public static final Parcelable.Creator<Link> CREATOR
            = new Parcelable.Creator<Link>() {
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    public Link(){}

    private Link(Parcel in)
    {
        this.id = in.readLong();
        this.url = in.readString();
        this.isRead = in.readByte() != 0;
        this.isImportant = in.readByte() != 0;
    }

    @Override
    public String toString() {
        return String.format("URL: %s, isRead: %s, isImportant: %s",
                this.url, this.isRead, this.isImportant);
    }
}
