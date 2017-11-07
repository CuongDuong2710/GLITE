package com.glite.popeyes.view.menu.our_menu.model;

import android.view.View;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Addon;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.GroupInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUOC CUONG on 27/09/2016.
 */
public class GroupParent extends GroupInfo implements ParentListItem {

    private List<Addon> mAddOns;

    public GroupParent() {
    }

    public GroupParent(GroupInfo groupInfo, List<Addon> mAddOns) {
        this.mAddOns = new ArrayList<>();
    }

    @Override
    public List<Addon> getChildItemList() {
        return mAddOns;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
