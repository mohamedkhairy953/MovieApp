package com.example.moham.movieapp_volley.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer_ModelResults implements Parcelable{
    private String site;
    private int size;
    private String iso_3166_1;
    private String name;
    private String id;
    private String type;
    private String iso_639_1;
    private String key;

     Trailer_ModelResults(Parcel in) {
        site = in.readString();
        size = in.readInt();
        iso_3166_1 = in.readString();
        name = in.readString();
        id = in.readString();
        type = in.readString();
        iso_639_1 = in.readString();
        key = in.readString();
    }

     Trailer_ModelResults() {

    }

    public static final Creator<Trailer_ModelResults> CREATOR = new Creator<Trailer_ModelResults>() {
        @Override
        public Trailer_ModelResults createFromParcel(Parcel in) {
            return new Trailer_ModelResults(in);
        }

        @Override
        public Trailer_ModelResults[] newArray(int size) {
            return new Trailer_ModelResults[size];
        }
    };

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIso_3166_1() {
        return this.iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIso_639_1() {
        return this.iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(site);
        parcel.writeInt(size);
        parcel.writeString(iso_3166_1);
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(iso_639_1);
        parcel.writeString(key);
    }
}
