package com.glite.popeyes.data.remote.reponse.listordertime;

import com.glite.popeyes.data.remote.reponse.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/16/2016.
 */
public class ListOrderTimeResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }
}
