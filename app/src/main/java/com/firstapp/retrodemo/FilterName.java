package com.firstapp.retrodemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FilterName  {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("filter_group_id")
    @Expose
    private String filterGroupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("bus_image")
    @Expose
    private String busImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilterGroupId() {
        return filterGroupId;
    }

    public void setFilterGroupId(String filterGroupId) {
        this.filterGroupId = filterGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBusImage() {
        return busImage;
    }

    public void setBusImage(String busImage) {
        this.busImage = busImage;
    }

}
