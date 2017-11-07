
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuVariant {

    @SerializedName("VariantID")
    @Expose
    private String variantID;
    @SerializedName("VariantName")
    @Expose
    private String variantName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("PriceDifference")
    @Expose
    private String priceDifference;
    @SerializedName("AddonGroupID")
    @Expose
    private String addonGroupID;
    @SerializedName("VariantPrice")
    @Expose
    private Double variantPrice;
    @SerializedName("GroupAddons")
    @Expose
    private List<GroupAddon> groupAddons = new ArrayList<>();

    /**
     * 
     * @return
     *     The variantID
     */
    public String getVariantID() {
        return variantID;
    }

    /**
     * 
     * @param variantID
     *     The VariantID
     */
    public void setVariantID(String variantID) {
        this.variantID = variantID;
    }

    /**
     * 
     * @return
     *     The variantName
     */
    public String getVariantName() {
        return variantName;
    }

    /**
     * 
     * @param variantName
     *     The VariantName
     */
    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The priceDifference
     */
    public String getPriceDifference() {
        return priceDifference;
    }

    /**
     * 
     * @param priceDifference
     *     The PriceDifference
     */
    public void setPriceDifference(String priceDifference) {
        this.priceDifference = priceDifference;
    }

    /**
     * 
     * @return
     *     The addonGroupID
     */
    public String getAddonGroupID() {
        return addonGroupID;
    }

    /**
     * 
     * @param addonGroupID
     *     The AddonGroupID
     */
    public void setAddonGroupID(String addonGroupID) {
        this.addonGroupID = addonGroupID;
    }

    /**
     * 
     * @return
     *     The variantPrice
     */
    public Double getVariantPrice() {
        return variantPrice;
    }

    /**
     * 
     * @param variantPrice
     *     The VariantPrice
     */
    public void setVariantPrice(Double variantPrice) {
        this.variantPrice = variantPrice;
    }

    /**
     * 
     * @return
     *     The groupAddons
     */
    public List<GroupAddon> getGroupAddons() {
        return groupAddons;
    }

    /**
     * 
     * @param groupAddons
     *     The GroupAddons
     */
    public void setGroupAddons(List<GroupAddon> groupAddons) {
        this.groupAddons = groupAddons;
    }

}
