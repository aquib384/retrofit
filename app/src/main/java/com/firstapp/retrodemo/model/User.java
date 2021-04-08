
package com.firstapp.retrodemo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("statics")
    @Expose
    private List<Static> statics = null;
    @SerializedName("filterData")
    @Expose
    private List<FilterDatum> filterData = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public User(String responseCode, Response response, List<Static> statics, List<FilterDatum> filterData, List<Detail> details) {
        this.responseCode = responseCode;
        this.response = response;
        this.statics = statics;
        this.filterData = filterData;
        this.details = details;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public Response getResponse() {
        return response;
    }

    public List<Static> getStatics() {
        return statics;
    }

    public List<FilterDatum> getFilterData() {
        return filterData;
    }

    public List<Detail> getDetails() {
        return details;
    }
}
