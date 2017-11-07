package com.glite.popeyes.data.remote.reponse.authen;

import com.glite.popeyes.data.remote.reponse.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public class AuthenResponse extends BaseResponse {


    @SerializedName("data")
    private AuthenData data;

    /**
     * @return The data
     */
    public AuthenData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(AuthenData data) {
        this.data = data;
    }
}
