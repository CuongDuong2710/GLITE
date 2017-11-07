package com.glite.popeyes.data.remote.reponse.postalcode;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/5/2016.
 */
public class TatStatus {

    @SerializedName("StatusName")
    private String statusName;

    @SerializedName("DeliveryTime")
    private String deliveryTime;

    /**
     * @return The status name
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName The status name
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * @return The delivery time
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime The delivery time
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
