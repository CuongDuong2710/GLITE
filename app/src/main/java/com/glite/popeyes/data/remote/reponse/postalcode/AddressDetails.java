package com.glite.popeyes.data.remote.reponse.postalcode;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/5/2016.
 */
public class AddressDetails {

    @SerializedName("StreetName")
    private String streetName;

    @SerializedName("BuildingName")
    private String buildingName;

    @SerializedName("PostalCode")
    private String postalCode;

    @SerializedName("Found")
    private String found;

    @SerializedName("Outlet")
    private Outlet outlet;

    @SerializedName("TatStatus")
    private TatStatus tatStatus;

    /**
     * @return The street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName The street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return The building name
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @param buildingName The building name
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * @return The postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode The postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    /**
     * @return The outlet
     */
    public Outlet getOutlet() {
        return outlet;
    }

    /**
     * @param outlet The outlet
     */
    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    /**
     * @return The status tat
     */
    public TatStatus getTatStatus() {
        return tatStatus;
    }

    /**
     * @param tatStatus The status tat
     */
    public void setTatStatus(TatStatus tatStatus) {
        this.tatStatus = tatStatus;
    }
}
