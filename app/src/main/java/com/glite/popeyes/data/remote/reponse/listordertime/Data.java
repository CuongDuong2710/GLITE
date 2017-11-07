package com.glite.popeyes.data.remote.reponse.listordertime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/16/2016.
 */
public class Data {

    @SerializedName("outlet")
    @Expose
    private Outlet outlet;

    /**
     *
     * @return
     * The outlet
     */
    public Outlet getOutlet() {
        return outlet;
    }

    /**
     *
     * @param outlet
     * The outlet
     */
    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }
}
