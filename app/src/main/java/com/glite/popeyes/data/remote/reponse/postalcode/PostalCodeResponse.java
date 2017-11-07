package com.glite.popeyes.data.remote.reponse.postalcode;

import com.glite.popeyes.data.remote.reponse.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/5/2016.
 */
public class PostalCodeResponse extends BaseResponse {

    @SerializedName("data")
    private PostalCodeData postalCodeData;

    /**
     * @return The postal code data
     */
    public PostalCodeData getPostalCodeData() {
        return postalCodeData;
    }

    /**
     * @param postalCodeData The postal code data
     */
    public void setPostalCodeData(PostalCodeData postalCodeData) {
        this.postalCodeData = postalCodeData;
    }
}
