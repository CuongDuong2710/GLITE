
package com.glite.popeyes.data.remote.reponse.order_menu.category;

import com.google.gson.annotations.SerializedName;

public class CategoryItem {

    @SerializedName("CategoryID")
    private String categoryID;

    @SerializedName("CategoryName")
    private String categoryName;

    @SerializedName("Description")
    private String description;

    @SerializedName("ImageSource")
    private String imageSource;

    @SerializedName("CategoryType")
    private String categoryType;

    @SerializedName("Sequence")
    private String sequence;

    public void setCategory(CategoryItem categoryItem) {
        this.setCategoryID(categoryItem.getCategoryID());
        this.setCategoryName(categoryItem.getCategoryName());
        this.setCategoryType(categoryItem.getCategoryType());
        this.setDescription(categoryItem.getDescription());
        this.setImageSource(categoryItem.getImageSource());
        this.setSequence(categoryItem.getSequence());
    }

    /**
     * @return The categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID The CategoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return The categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName The CategoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The imageSource
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     * @param imageSource The ImageSource
     */
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    /**
     * @return The categoryType
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType The CategoryType
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
