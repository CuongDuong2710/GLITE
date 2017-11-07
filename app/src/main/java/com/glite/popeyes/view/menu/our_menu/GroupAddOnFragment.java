package com.glite.popeyes.view.menu.our_menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Addon;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.GroupAddon;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.GroupInfo;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.MenuVariant;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.CustomButtonIcon;
import com.glite.popeyes.view.menu.cart.BasketFragment;
import com.glite.popeyes.view.menu.our_menu.adapter.GroupAddOnAdapter;
import com.glite.popeyes.view.menu.our_menu.model.AddOnItem;
import com.glite.popeyes.view.menu.our_menu.model.Basket;
import com.glite.popeyes.view.menu.our_menu.model.OrderItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QUOC CUONG on 11/10/2016.
 */
public class GroupAddOnFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = "GroupAddOnFragment";

    public static Basket basket = new Basket("basket");

    @BindView(R.id.img_submenu)
    ImageView mImgSubMenu;
    @BindView(R.id.description_submenu)
    TextView mTxtDescription;
    @BindView(R.id.list_view_expanded)
    ExpandableListView expListView;
    @BindView(R.id.btn_add_to_order)
    CustomButtonIcon mBtnAddToOrder;
    @BindView(R.id.recycle_group_add_on)
    RecyclerView mRecyclerView;

     // data to show on screen
    private List<GroupInfo> mGroupInfos = new ArrayList<>();
    private HashMap<GroupInfo, List<AddOnItem>> mData;
    private int lastExpandedPosition  = -1;
    ArrayList<AddOnItem> mAddOnItems;

     // Data of each group
    ArrayList<Addon> mAddOns;
    Map<GroupInfo, List<Addon>> mDataGroupAddon = new HashMap<>();

     // Item selected of each group
    final Map<GroupInfo, List<Addon>> mDataGroupAddonSelected = new HashMap<>();

    // Number item selected of each group
    Map<GroupInfo, Integer> mNumberOfSelectedItem = new HashMap<>();

    Item item;

    public static GroupAddOnFragment newInstance() {
        GroupAddOnFragment fragment = new GroupAddOnFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mData = new HashMap<>();
        mGroupInfos = new ArrayList<>();

        final GroupAddOnAdapter adapter = new GroupAddOnAdapter(getContext(), mGroupInfos, mData);
        expListView.setAdapter(adapter);

        //add data
        item = getAppComponent().orderManager().getItem();
        if (item != null) {
            addDataGroupAddOns(item);
        }

        onGroupAddOnListener(adapter);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_order_delivery_customize;
    }

    @Override
    public void injectComponent() {

    }

    /**
     * Group AddOn listener
     * @param adapter
     */
    private void onGroupAddOnListener(GroupAddOnAdapter adapter) {

        expListView.setOnGroupClickListener((expandableListView, view, i, l) -> false);

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {

                // get AddOn is selected
                toogleSelection(groupPosition, childPosition);

                // print data
                prettyPrintSelected(adapter);

                // get imageView widget
                ImageView currentImageView = (ImageView) view.findViewById(R.id.img_check_add_on);

                // get item is click
                AddOnItem item = mData.get(mGroupInfos.get(groupPosition)).get(childPosition);
                if (item.isChecked()) {
                    item.setChecked(false);
                    currentImageView.setVisibility(View.GONE);
                } else {
                    item.setChecked(true);
                    currentImageView.setVisibility(View.VISIBLE);
                }
//                ToastUtil.showSingleToast(getContext(), "childPosition: " + childPosition);

                return false;
            }
        });
    }

    int numberOfSelected = 0;
    /**
     * Save Addons is selected into map
     * @param groupPosition
     * @param childPosition
     */
    public void toogleSelection(int groupPosition, int childPosition) {

        // get groupInfo
        GroupInfo groupInfo = mGroupInfos.get(groupPosition);
        int maximum = Integer.parseInt(groupInfo.getMaximum());
        int minumum = Integer.parseInt(groupInfo.getMinimum());


        // get list addon by groupInfo
        List<Addon> addons = mDataGroupAddon.get(groupInfo);

        // get addon is selected
        Addon addon = addons.get(childPosition);

        // get list addons by groupInfo key
        List<Addon> sel = mDataGroupAddonSelected.get(groupInfo);

        int numberSelected = mNumberOfSelectedItem.get(groupInfo);

        if (numberSelected > maximum) {
            ToastUtil.showSingleToast(getContext(), "Cannot select than " + maximum);
        }

        if (numberSelected < minumum) {
            ToastUtil.showSingleToast(getContext(), "Plese select minimum " + minumum);
        }

        if (sel == null) {
            sel = new ArrayList<>();
            mDataGroupAddonSelected.put(groupInfo, sel);
        }

        if (sel.contains(addon)) {
            sel.remove(addon);
            numberSelected--;
        } else {
            sel.add(addon);
            numberSelected++;
        }
        mNumberOfSelectedItem.put(groupInfo, numberSelected);
        ToastUtil.showSingleToast(getContext(), numberSelected + "");
    }

    private String prettyPrintSelected(GroupAddOnAdapter adapter) {
        Map<GroupInfo, List<Addon>> selectedItems = mDataGroupAddonSelected;
        String result = "  \nSelected Items in list: \n";
        for (GroupInfo group : selectedItems.keySet()) {
            List<Addon> items = selectedItems.get(group);
            if (items.size() > 0) {
                result += "\n" + group.getGroupName() + ": ";
                for (Addon item : items) {
                    result += item + ", ";
                }
            }
        }
        Log.d("CuongDNQ", result);
        return result;
    }

    @OnClick(R.id.btn_add_to_order)
    public void onAddToOrderClicked() {

        // add item to basket
        addItemToBasket();

        // replace fragment
        replaceFragment();

    }

    /**
     * Replace fragment to basket
     */
    private void replaceFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.order_delivery_container);
        if (fragment != null) {
            getFragmentManager().beginTransaction().hide(fragment).commit();
        }

        BasketFragment orderDeliveryBasketFragment = BasketFragment.newInstance();
        replaceFragment(R.id.order_delivery_container, orderDeliveryBasketFragment, BasketFragment.FRAGMENT_TAG);
    }

    /**
     * Add item to basket
     */
    private void addItemToBasket() {
        double priceAddOns = getPriceAddOnsSelected();
        double priceItem = Double.parseDouble(item.getUnitPrice()) + priceAddOns;

        Bitmap bitmap = setBitMap(item.getImageSource());
        OrderItem orderItem = new OrderItem(item.getMenuID(), item.getMenuName(), bitmap, item.getDescription(), priceItem, 1);
        basket.addItem(orderItem);
    }

    /**
     * Collect addons is selected
     * @return collection of addons
     */
    private Collection getListAddOns() {
        return mDataGroupAddonSelected.values();
    }

    /**
     * Calculate prices of addons is selected
     * @return PriceDifference
     */
    private double getPriceAddOnsSelected() {
        double price = 0.0d;
        Iterator<List<Addon>> iterator = getListAddOns().iterator();

        while (iterator.hasNext()) {
            for(Addon a : iterator.next()){
                price += Double.parseDouble(a.getPriceDifference());
            }
        }
        return price;
    }

    /**
     * Get data from API
     * @param item
     */
    private void addDataGroupAddOns(Item item) {

        int i = 0;

        List<MenuVariant> menuVariants = item.getMenuVariant();

        //set data for text description and image view
        setImageBitMap(item.getImageSource());
        mTxtDescription.setText(item.getDescription());

        if (!CheckerUtil.isEmptyList(menuVariants)) {
            for (MenuVariant m : menuVariants) {

                List<GroupAddon> groupAddons = m.getGroupAddons();

                if (!CheckerUtil.isEmptyList(groupAddons)) {
                    for (GroupAddon g : groupAddons) {

                        mAddOns = new ArrayList<>();
                        mAddOnItems = new ArrayList<>();

                        GroupInfo groupInfo = g.getGroupInfo();
                        if (groupInfo != null) {

                            mGroupInfos.add(groupInfo);
                            mNumberOfSelectedItem.put(groupInfo, numberOfSelected);
                        }

                        List<Addon> addons = g.getAddon();

                        if (!CheckerUtil.isEmptyList(addons)) {

                            mAddOns.addAll(addons);
                            mDataGroupAddon.put(groupInfo, mAddOns);

                            for (Addon a : addons) {
                                AddOnItem addOnItem = new AddOnItem(a.getAddOnName(), R.drawable.child_item_check, a.getShowType());
                                mAddOnItems.add(addOnItem);
                            }

                            mData.put(mGroupInfos.get(i), mAddOnItems);
                            i++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Set bitmap
     * @param url
     */
    private void setImageBitMap(String url) {
        try {
            if (url != null) {
                URL newUrl = new URL(url);
                Bitmap bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());

                if (bitmap != null) {
                    mImgSubMenu.setImageBitmap(bitmap);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set bitmap
     * @param url
     * @return bitmap
     */
    private Bitmap setBitMap(String url) {
        try {
            if (url != null) {
                URL newUrl = new URL(url);
                Bitmap bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());

                if (bitmap != null) {
                    return bitmap;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
