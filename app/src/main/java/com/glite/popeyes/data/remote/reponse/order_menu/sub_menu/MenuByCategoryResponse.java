
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import com.glite.popeyes.data.remote.reponse.base.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuByCategoryResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
