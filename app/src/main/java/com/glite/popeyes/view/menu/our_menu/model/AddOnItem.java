package com.glite.popeyes.view.menu.our_menu.model;

/**
 * Created by QUOC CUONG on 11/10/2016.
 */
public class AddOnItem  {

    private String textAddOnName;
    private int imgCheck;
    private boolean isChecked;
    private String showType;

    public AddOnItem(String textAddOnName, int imgCheck, String showType) {
        this.textAddOnName = textAddOnName;
        this.imgCheck = imgCheck;
        this.showType = showType;
    }

    public String getTextAddOnName() {
        return textAddOnName;
    }

    public void setTextAddOnName(String textAddOnName) {
        this.textAddOnName = textAddOnName;
    }

    public int getImgCheck() {
        return imgCheck;
    }

    public void setImgCheck(int imgCheck) {
        this.imgCheck = imgCheck;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return "AddOnItem{" +
                "textAddOnName='" + textAddOnName + '\'' +
                ", imgCheck=" + imgCheck +
                ", isChecked=" + isChecked +
                ", showType=" + showType +
                '}';
    }
}
