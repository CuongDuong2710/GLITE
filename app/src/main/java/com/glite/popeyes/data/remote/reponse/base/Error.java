package com.glite.popeyes.data.remote.reponse.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public class Error {

    @SerializedName("code")
    private Integer code;

    @SerializedName("msg")
    private String msg;

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
