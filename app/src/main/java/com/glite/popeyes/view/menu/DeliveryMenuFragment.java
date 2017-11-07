package com.glite.popeyes.view.menu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.glite.popeyes.R;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.FuturaButton;
import com.glite.popeyes.view.menu.our_menu.OurMenuFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryMenuFragment extends BaseFragment {

    @BindView(R.id.button_our_menu)
    FuturaButton mOurMenuButton;
    @BindView(R.id.button_cart)
    LinearLayout mCartButton;
    @BindView(R.id.button_checkout)
    FuturaButton mCheckoutButton;

    public DeliveryMenuFragment() {
        // Required empty public constructor
    }

    public static DeliveryMenuFragment newInstance() {
        return new DeliveryMenuFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_order_delivery_menu;
    }

    @Override
    public void injectComponent() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addOurMenu();
    }

    private void addOurMenu() {
        OurMenuFragment ourMenuFragment = OurMenuFragment.newInstance();
        replaceChildFragment(R.id.order_delivery_container, ourMenuFragment);
    }

    @OnClick(R.id.button_our_menu)
    public void showOurMenu() {
        // TODO: impl later
    }

    @OnClick(R.id.button_cart)
    public void showCart() {
        // TODO: impl later
    }

    @OnClick(R.id.button_checkout)
    public void showCheckOut() {
        // TODO: impl later
    }

    @OnClick(R.id.button_arrow)
    public void loadMore() {
        // TODO: call api load more data
    }


}
