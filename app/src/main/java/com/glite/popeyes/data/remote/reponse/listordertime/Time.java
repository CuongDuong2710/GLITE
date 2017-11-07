package com.glite.popeyes.data.remote.reponse.listordertime;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 9/16/2016.
 */
public class Time {

    @SerializedName("value")
    private String value;

    @SerializedName("text")
    private String text;

    /**
     *
     * @return The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }
}
