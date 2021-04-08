
package com.firstapp.retrodemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterName {

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

    public FilterName(String id, String filterGroupId, String name, String image, String busImage) {
        this.id = id;
        this.filterGroupId = filterGroupId;
        this.name = name;
        this.image = image;
        this.busImage = busImage;
    }

    public String getId() {
        return id;
    }

    public String getFilterGroupId() {
        return filterGroupId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getBusImage() {
        return busImage;
    }
}
