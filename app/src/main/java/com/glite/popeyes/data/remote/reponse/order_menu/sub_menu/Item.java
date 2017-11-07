
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("MenuName")
    @Expose
    private String menuName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ImageSource")
    @Expose
    private String imageSource;
    @SerializedName("UnitPrice")
    @Expose
    private String unitPrice;
    @SerializedName("PLUCODE")
    @Expose
    private String pLUCODE;
    @SerializedName("Sequence")
    @Expose
    private String sequence;
    @SerializedName("MenuVariant")
    @Expose
    private List<MenuVariant> menuVariant = new ArrayList<MenuVariant>();

    /**
     * 
     * @return
     *     The menuID
     */
    public String getMenuID() {
        return menuID;
    }

    /**
     * 
     * @param menuID
     *     The MenuID
     */
    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    /**
     * 
     * @return
     *     The menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 
     * @param menuName
     *     The MenuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
     *     The imageSource
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     * 
     * @param imageSource
     *     The ImageSource
     */
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    /**
     * 
     * @return
     *     The unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * 
     * @param unitPrice
     *     The UnitPrice
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 
     * @return
     *     The pLUCODE
     */
    public String getPLUCODE() {
        return pLUCODE;
    }

    /**
     * 
     * @param pLUCODE
     *     The PLUCODE
     */
    public void setPLUCODE(String pLUCODE) {
        this.pLUCODE = pLUCODE;
    }

    /**
     * 
     * @return
     *     The sequence
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * 
     * @param sequence
     *     The Sequence
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * 
     * @return
     *     The menuVariant
     */
    public List<MenuVariant> getMenuVariant() {
        return menuVariant;
    }

    /**
     * 
     * @param menuVariant
     *     The MenuVariant
     */
    public void setMenuVariant(List<MenuVariant> menuVariant) {
        this.menuVariant = menuVariant;
    }

}
