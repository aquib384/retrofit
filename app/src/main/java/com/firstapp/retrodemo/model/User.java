
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

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Static> getStatics() {
        return statics;
    }

    public void setStatics(List<Static> statics) {
        this.statics = statics;
    }

    public List<FilterDatum> getFilterData() {
        return filterData;
    }

    public void setFilterData(List<FilterDatum> filterData) {
        this.filterData = filterData;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

}
