
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupInfo {

    @SerializedName("GroupID")
    @Expose
    private String groupID;
    @SerializedName("GroupName")
    @Expose
    private String groupName;
    @SerializedName("Maximum")
    @Expose
    private String maximum;
    @SerializedName("Minimum")
    @Expose
    private String minimum;

    /**
     * @return The groupID
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * @param groupID The GroupID
     */
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    /**
     * @return The groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName The GroupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return The maximum
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     * @param maximum The Maximum
     */
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    /**
     * @return The minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     * @param minimum The Minimum
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}
