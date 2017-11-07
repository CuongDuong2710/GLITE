
package com.glite.popeyes.data.remote.reponse.order_menu.category;

import com.glite.popeyes.data.remote.reponse.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse extends BaseResponse {

    @SerializedName("data")
    private CategoryData data;

    /**
     * @return The data
     */
    public CategoryData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(CategoryData data) {
        this.data = data;
    }

}
