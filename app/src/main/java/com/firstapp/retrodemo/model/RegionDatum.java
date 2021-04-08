
package com.firstapp.retrodemo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegionDatum {

    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("totalInfected")
    @Expose
    private Integer totalInfected;
    @SerializedName("newInfected")
    @Expose
    private Integer newInfected;

    public RegionDatum(String region, Integer totalInfected, Integer newInfected) {
        this.region = region;
        this.totalInfected = totalInfected;
        this.newInfected = newInfected;
    }

    public String getRegion() {
        return region;
    }

    public Integer getTotalInfected() {
        return totalInfected;
    }

    public Integer getNewInfected() {
        return newInfected;
    }
}
