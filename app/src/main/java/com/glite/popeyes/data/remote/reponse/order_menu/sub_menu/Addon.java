
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addon {

    @SerializedName("AddOnID")
    @Expose
    private String addOnID;
    @SerializedName("AddOnName")
    @Expose
    private String addOnName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("PriceDifference")
    @Expose
    private String priceDifference;
    @SerializedName("ShowType")
    @Expose
    private String showType;

    /**
     * @return The addOnID
     */
    public String getAddOnID() {
        return addOnID;
    }

    /**
     * @param addOnID The AddOnID
     */
    public void setAddOnID(String addOnID) {
        this.addOnID = addOnID;
    }

    /**
     * @return The addOnName
     */
    public String getAddOnName() {
        return addOnName;
    }

    /**
     * @param addOnName The AddOnName
     */
    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The priceDifference
     */
    public String getPriceDifference() {
        return priceDifference;
    }

    /**
     * @param priceDifference The PriceDifference
     */
    public void setPriceDifference(String priceDifference) {
        this.priceDifference = priceDifference;
    }

    /**
     * @return The showType
     */
    public String getShowType() {
        return showType;
    }

    /**
     * @param showType The ShowType
     */
    public void setShowType(String showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return "Addon{" +
                "addOnID='" + addOnID + '\'' +
                ", addOnName='" + addOnName + '\'' +
                ", description='" + description + '\'' +
                ", priceDifference='" + priceDifference + '\'' +
                ", showType='" + showType + '\'' +
                '}';
    }
}
