
package com.glite.popeyes.data.remote.reponse.order_menu.sub_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("menu")
    @Expose
    private Menu menu;

    /**
     * @return The menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu The menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
