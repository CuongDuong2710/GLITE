
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupAddon {

    @SerializedName("0")
    @Expose
    private GroupInfo groupInfo;

    @SerializedName("Addon")
    @Expose
    private List<Addon> addon = new ArrayList<>();

    /**
     * @return the GroupInfo
     */
    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    /**
     * @param
     */
    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    /**
     * @return The addon
     */
    public List<Addon> getAddon() {
        return addon;
    }

    /**
     * @param addon The Addon
     */
    public void setAddon(List<Addon> addon) {
        this.addon = addon;
    }

}
