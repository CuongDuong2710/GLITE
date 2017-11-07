
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
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
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The maxPage
     */
    public String getMaxPage() {
        return maxPage;
    }

    /**
     * 
     * @param maxPage
     *     The maxPage
     */
    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    /**
     * 
     * @return
     *     The maxItem
     */
    public String getMaxItem() {
        return maxItem;
    }

    /**
     * 
     * @param maxItem
     *     The maxItem
     */
    public void setMaxItem(String maxItem) {
        this.maxItem = maxItem;
    }

    /**
     * 
     * @return
     *     The limitItem
     */
    public String getLimitItem() {
        return limitItem;
    }

    /**
     * 
     * @param limitItem
     *     The limitItem
     */
    public void setLimitItem(String limitItem) {
        this.limitItem = limitItem;
    }

}
