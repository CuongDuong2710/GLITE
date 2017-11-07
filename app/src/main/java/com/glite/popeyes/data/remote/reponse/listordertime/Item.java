package com.glite.popeyes.data.remote.reponse.listordertime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 9/16/2016.
 */
public class Item {

    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("times")
    @Expose
    private List<Time> times = new ArrayList<Time>();

    /**
     *
     * @return
     * The date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The times
     */
    public List<Time> getTimes() {
        return times;
    }

    /**
     *
     * @param times
     * The times
     */
    public void setTimes(List<Time> times) {
        this.times = times;
    }
}
