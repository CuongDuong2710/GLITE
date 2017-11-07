package com.glite.popeyes.data.remote.reponse.postalcode;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/5/2016.
 */
public class PostalCodeData {

    @SerializedName("addressdetails")
    private AddressDetails addressDetails;

    /**
     * @return The address details
     */
    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

    /**
     * @param addressDetails The address details
     */
    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }
}
