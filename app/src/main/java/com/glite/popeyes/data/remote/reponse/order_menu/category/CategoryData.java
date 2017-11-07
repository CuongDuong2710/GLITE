
package com.glite.popeyes.data.remote.reponse.order_menu.category;

import com.google.gson.annotations.SerializedName;

public class CategoryData {

    @SerializedName("categorylist")
    private CategoryList categoryList;

    /**
     * @return The categoryList
     */
    public CategoryList getCategoryList() {
        return categoryList;
    }

    /**
     * @param categoryList The categoryList
     */
    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

}
