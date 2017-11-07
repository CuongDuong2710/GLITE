package com.glite.popeyes.data.local;

import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Menu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Brian
 * @date: 07/09/2016
 */
@Singleton
public class OrdersManger {

    private static final String PREF_KEY_MENU = "pref_key_menu";

    private final Gson mGson;
    private final PreferenceHelper mSharedHelper;

    @Inject
    OrdersManger(Gson gson, PreferenceHelper preferenceHelper) {
        this.mGson = gson;
        this.mSharedHelper = preferenceHelper;
    }

    public void putItem(Item item) {
        final String jSonItem = mGson.toJson(item);
        mSharedHelper.putString(PREF_KEY_MENU, jSonItem);
    }

    public Item getItem() {
        final String jSonItem = mSharedHelper.getString(PREF_KEY_MENU, null);
        if (jSonItem == null) {
            return null;
        }
        return mGson.fromJson(jSonItem, Item.class);
    }

}
