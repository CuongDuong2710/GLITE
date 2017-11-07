package com.glite.popeyes.data.remote.reponse.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public class BaseResponse {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("error")
    private Error error;

    /**
     * @return The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return The error
     */
    public Error getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(Error error) {
        this.error = error;
    }
}
