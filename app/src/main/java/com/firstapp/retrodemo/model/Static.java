
package com.firstapp.retrodemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Static {

    @SerializedName("total_customer")
    @Expose
    private String totalCustomer;
    @SerializedName("this_week")
    @Expose
    private String thisWeek;
    @SerializedName("this_month")
    @Expose
    private String thisMonth;

    public String getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(String totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public String getThisWeek() {
        return thisWeek;
    }

    public void setThisWeek(String thisWeek) {
        this.thisWeek = thisWeek;
    }

    public String getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(String thisMonth) {
        this.thisMonth = thisMonth;
    }

}
