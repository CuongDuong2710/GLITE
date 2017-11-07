package com.glite.popeyes.view.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.ToolBarBaseActivity;
import com.glite.popeyes.view.custom.FuturaButton;
import com.glite.popeyes.view.menu.our_menu.OurMenuFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class DeliveryMenuActivity extends ToolBarBaseActivity {

    @BindView(R.id.button_our_menu)
    FuturaButton mOurMenuButton;
    @BindView(R.id.button_cart)
    LinearLayout mCartButton;
    @BindView(R.id.button_checkout)
    FuturaButton mCheckoutButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DeliveryMenuActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OurMenuFragment fragment = new OurMenuFragment();
        addFragment(R.id.order_delivery_container, fragment, OurMenuFragment.FRAGMENT_TAG);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_delivery_menu;
    }

    @Override
    public void injectComponent() {

    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @OnClick(R.id.button_cart)
    public void showCart() {
        // TODO: impl later
    }

    @OnClick(R.id.button_checkout)
    public void showCheckOut() {
        // TODO: impl later
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.order_delivery_container);

        if (fragment != null && fragment.isHidden()) {
            transaction.show(fragment).commit();
        } else {
            finish();
        }
    }

    @Override
    public void navigateBackClick() {
        super.navigateBackClick();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.order_delivery_container);

        if (fragment != null && fragment.isHidden()) {
            transaction.show(fragment).commit();
        }
    }

}
