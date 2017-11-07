package com.glite.popeyes.view.menu.our_menu.model;

import android.support.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryItem;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian
 * @date: 19/09/2016
 */

public class Category extends CategoryItem implements ParentListItem, Comparable<Category> {

    private List<Item> mSubMenu;

    public Category() {}

    public Category(CategoryItem categoryItem, List<Item> subMenuList) {
        setCategory(categoryItem);
        this.mSubMenu = new ArrayList<>();
    }

    public void setChildItemList(List<Item> mSubMenu) {
        this.mSubMenu = mSubMenu;
    }

    public static Category create(CategoryItem categoryItem) {
        Category category = new Category();
        category.setCategoryID(categoryItem.getCategoryID());
        category.setCategoryName(categoryItem.getCategoryName());
        category.setDescription(categoryItem.getDescription());
        category.setImageSource(categoryItem.getImageSource());
        category.setCategoryType(categoryItem.getCategoryType());
        category.setSequence(categoryItem.getSequence());
        return category;
    }

    @Override
    public List<?> getChildItemList() {
        return mSubMenu;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public int compareTo(@NonNull Category category) {
        int sequence1 = Integer.parseInt(category.getSequence());
        int sequence2 = Integer.parseInt(getSequence());
        if (sequence2 > sequence1) {
            return 1;
        } else if (sequence2 < sequence1) {
            return -1;
        } else {
            return 0;
        }
    }

    public List<Item> getmSubMenu() {
        return mSubMenu;
    }

    public void setmSubMenu(List<Item> mSubMenu) {
        this.mSubMenu = mSubMenu;
    }
}
