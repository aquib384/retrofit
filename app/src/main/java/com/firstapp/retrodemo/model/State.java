
package com.firstapp.retrodemo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class State {

    @SerializedName("activeCases")
    @Expose
    private Integer activeCases;

    @SerializedName("regionData")
    @Expose
    private List<RegionDatum> regionData = null;

    public State(Integer activeCases,List<RegionDatum> regionData) {
        this.activeCases = activeCases;

        this.regionData = regionData;
    }

    public State(String r) {

    }


    public Integer getActiveCases() {
        return activeCases;
    }



    public List<RegionDatum> getRegionData() {
        return regionData;
    }
}
