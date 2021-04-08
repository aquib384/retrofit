
package com.firstapp.retrodemo.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("filter_name")
    @Expose
    private List<FilterName> filterName = null;

    public FilterDatum(String id, String name, String description, String level, List<FilterName> filterName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.filterName = filterName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public List<FilterName> getFilterName() {
        return filterName;
    }
}
