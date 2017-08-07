package com.ivacompany.testtv.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iva on 07.08.17.
 */

public class Telecast {

    private int id;

    private String name;

    @SerializedName("icon")
    private String iconUrl;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
