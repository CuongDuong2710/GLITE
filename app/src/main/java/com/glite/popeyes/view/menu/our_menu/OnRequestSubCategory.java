package com.glite.popeyes.view.menu.our_menu;

import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;
import com.glite.popeyes.view.menu.our_menu.model.Category;

import java.util.List;

/**
 * @author Brian
 * @date: 19/09/2016
 */

public interface OnRequestSubCategory {
    void setSubMenu(Category category, List<Item> subItems);
}
