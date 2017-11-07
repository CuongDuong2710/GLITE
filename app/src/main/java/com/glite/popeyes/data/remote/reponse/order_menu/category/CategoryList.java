
package com.glite.popeyes.data.remote.reponse.order_menu.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    @SerializedName("items")
    @Expose
    private List<CategoryItem> items = new ArrayList<>();

    @SerializedName("maxPage")
    @Expose

    private String maxPage;
    @SerializedName("maxItem")

    @Expose
    private String maxItem;

    @SerializedName("limitItem")
    @Expose
    private String limitItem;

    /**
     * @return The items
     */
    public List<CategoryItem> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<CategoryItem> items) {
        this.items = items;
    }

    /**
     * @return The maxPage
     */
    public String getMaxPage() {
        return maxPage;
    }

    /**
     * @param maxPage The maxPage
     */
    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    /**
     * @return The maxItem
     */
    public String getMaxItem() {
        return maxItem;
    }

    /**
     * @param maxItem The maxItem
     */
    public void setMaxItem(String maxItem) {
        this.maxItem = maxItem;
    }

    /**
     * @return The limitItem
     */
    public String getLimitItem() {
        return limitItem;
    }

    /**
     * @param limitItem The limitItem
     */
    public void setLimitItem(String limitItem) {
        this.limitItem = limitItem;
    }

}
