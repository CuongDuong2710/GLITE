package com.glite.popeyes.data.remote.reponse.postalcode;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/5/2016.
 */
public class Outlet {

    @SerializedName("Day")
    private String day;

    @SerializedName("OpenTime")
    private String openTime;

    @SerializedName("CloseTime")
    private String closeTime;

    @SerializedName("OutletCode")
    private String outletCode;

    /**
     * @return The day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day The day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return The open time
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * @param openTime The open time
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     * @return The close time
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime The close time
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @return The outlet code
     */
    public String getOutletCode() {
        return outletCode;
    }

    /**
     * @param outletCode The outlet code
     */
    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }
}
