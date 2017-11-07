package com.glite.popeyes.view.menu.cart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.font.FontManager;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.FuturaButton;
import com.glite.popeyes.view.menu.checkout.CheckOutFragment;
import com.glite.popeyes.view.menu.our_menu.GroupAddOnFragment;
import com.glite.popeyes.view.menu.our_menu.OurMenuFragment;
import com.glite.popeyes.view.menu.our_menu.adapter.OrderBasketAdapter;
import com.glite.popeyes.view.menu.our_menu.model.OrderItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 9/21/2016.
 */
public class BasketFragment extends BaseFragment implements OrderBasketAdapter.OnButtonClickListener{

    public static final String FRAGMENT_TAG = "BasketFragment";

    @BindView(R.id.text_promotion_code_label)
    TextView mTextPromotionCode;
    @BindView(R.id.text_subtotal)
    TextView mTextSubTotal;
    @BindView(R.id.text_delivery)
    TextView mTextDelivery;
    @BindView(R.id.text_total)
    TextView mTextTotal;
    @BindView(R.id.text_promotion_code)
    EditText mEditPromotionCode;
    @BindView(R.id.sub_total_value)
    TextView mTextSubTotalValue;
    @BindView(R.id.delivery_value)
    TextView mTextDeliveryValue;
    @BindView(R.id.total_value)
    TextView mTextTotalValue;
    @BindView(R.id.button_add_more_order)
    FuturaButton mBtnAddMoreOrder;
    @BindView(R.id.button_checkout)
    FuturaButton mBtnCheckOut;

    private RecyclerView mRecycleBasket;
    private OrderBasketAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static List<OrderItem> orderItemList = new ArrayList<>();

    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set font
        setTypeface();

        // show data
        showData();

        // connect view
        mRecycleBasket = (RecyclerView) view.findViewById(R.id.rev_cart);
        // if the size of views will not change as the data changes.
        mRecycleBasket.setHasFixedSize(true);
        // setting the LayoutManager.
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycleBasket.setLayoutManager(mLayoutManager);
        // specify an adapter
        mAdapter = new OrderBasketAdapter(orderItemList, getContext());
        mRecycleBasket.setAdapter(mAdapter);

        mAdapter.setOnButtonClickListener(this);

        // set value total
        setUpValueTotal();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_basket;
    }

    @Override
    public void injectComponent() {

    }

    @OnClick(R.id.button_add_more_order)
    public void onAddMoreOrderClicked() {
        getFragmentManager().popBackStack(OurMenuFragment.FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        OurMenuFragment ourMenuFragment = OurMenuFragment.newInstance();
        replaceFragment(R.id.order_delivery_container, ourMenuFragment, OurMenuFragment.FRAGMENT_TAG);
    }

    @OnClick(R.id.button_checkout)
    public void onCheckOutClicked() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.order_delivery_container);
        if (fragment != null) {
            getFragmentManager().beginTransaction().hide(fragment).commit();
        }

        CheckOutFragment checkOutFragment = CheckOutFragment.newInstance();
        replaceFragment(R.id.order_delivery_container, checkOutFragment, "TAG_C");
    }

    private void setTypeface() {
        mTextPromotionCode.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextSubTotal.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextDelivery.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextTotal.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mEditPromotionCode.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextSubTotalValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextDeliveryValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
        mTextTotalValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getActivity()));
    }

    private void showData() {
        orderItemList.clear();
        Iterator<OrderItem> iterator = GroupAddOnFragment.basket.getCartDetails().iterator();
        while (iterator.hasNext()) {
            orderItemList.add(iterator.next());
        }
    }

    public void setUpValueTotal() {
        double subTotal = GroupAddOnFragment.basket.getCartPrice();
        double deliveryFree = Double.parseDouble(mTextDeliveryValue.getText().toString().substring(1));
        double total = subTotal + deliveryFree;

        mTextSubTotalValue.setText("$" + subTotal);
        mTextTotalValue.setText("$" + total);
    }

    @Override
    public void onAddButtonClick(double itemTotal) {
        double subTotal = GroupAddOnFragment.basket.getCartPrice();
        subTotal += itemTotal;
        double deliveryFree = Double.parseDouble(mTextDeliveryValue.getText().toString().substring(1));
        double total = subTotal + deliveryFree;

        mTextSubTotalValue.setText("$" + subTotal);
        mTextTotalValue.setText("$" + total);
    }

    @Override
    public void onMinusButtonClick(double itemTotal) {
//        double subTotal = OrderDeliveryCustomizeFragment.basket.getCartPrice();
//        subTotal -= itemTotal;
//        double deliveryFree = Double.parseDouble(mTextDeliveryValue.getText().toString().substring(1));
//        double total = subTotal + deliveryFree;
//
//        mTextSubTotalValue.setText("$" + subTotal);
//        mTextTotalValue.setText("$" + total);
    }
}
