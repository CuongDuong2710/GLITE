package com.glite.popeyes.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.glite.popeyes.R;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.ToolBarBaseActivity;
import com.glite.popeyes.view.forgot_password.ForgotPasswordFragment;
import com.glite.popeyes.view.guest.GuestLoginFragment;
import com.glite.popeyes.view.home.HomeDeliveryFragment;
import com.glite.popeyes.view.menu.DeliveryMenuFragment;
import com.glite.popeyes.view.register.RegisterAccountFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends ToolBarBaseActivity implements NavigationView.OnNavigationItemSelectedListener, Constants {

    private static final String EXTRA_FROM = "extra_from";

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private int mFrom;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static void start(Context context, int from) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_FROM, from);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpNavigationView();

        mFrom = getIntent().getIntExtra(EXTRA_FROM, 1);


        selectedItem(mFrom);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUpToolbar() {

    }

    private boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.END);
    }

    private void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    private void openDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(GravityCompat.END);
        }
    }

    private void setUpNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.icon_navigation_drawer)
    public void openDrawerClick() {
        openDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            ToastUtil.showSingleToast(this, "camera");
        } else if (id == R.id.nav_gallery) {
            ToastUtil.showSingleToast(this, "gallery");

        } else if (id == R.id.nav_slideshow) {
            ToastUtil.showSingleToast(this, "slideShow");

        } else if (id == R.id.nav_manage) {
            ToastUtil.showSingleToast(this, "Manage");
        }

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null) {
            if (isNavDrawerOpen()) {
                closeNavDrawer();
            } else {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count > 1) {
                    super.onBackPressed();
                } else {
                    finish();
                }
            }
        }
    }

    private void selectedItem(int from) {
        Fragment fragment = null;
        String tag = null;
        switch (from) {
            case Constants.EXTRA_FROM.ORDER_DELIVERY_MENU_FRAGMENT:
                fragment = DeliveryMenuFragment.newInstance();
                tag = String.valueOf(Constants.EXTRA_FROM.ORDER_DELIVERY_MENU_FRAGMENT);
                break;
            case Constants.EXTRA_FROM.ACCOUNT_REGISTER_FRAGMENT:
                fragment = RegisterAccountFragment.newInstance();
                tag = String.valueOf(Constants.EXTRA_FROM.ACCOUNT_REGISTER_FRAGMENT);
                break;
            case Constants.EXTRA_FROM.GUEST_FRAGMENT:
                fragment = GuestLoginFragment.newInstance();
                tag = String.valueOf(Constants.EXTRA_FROM.GUEST_FRAGMENT);
                break;
            case Constants.EXTRA_FROM.HOME_DELIVERY_1_FRAGMENT:
                fragment = HomeDeliveryFragment.newInstance();
                tag = String.valueOf(Constants.EXTRA_FROM.HOME_DELIVERY_1_FRAGMENT);
                break;
            case Constants.EXTRA_FROM.FORGOT_PASS_FRAGMENT:
                fragment = ForgotPasswordFragment.newInstance();
                tag = String.valueOf(Constants.EXTRA_FROM.FORGOT_PASS_FRAGMENT);
                break;
            default:
                break;

        }

        if (fragment != null) {
            showFragment(fragment, tag);
        }

    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_container, fragment, tag)
                .addToBackStack(tag).commit();
    }

    @Override
    public void injectComponent() {

    }


}
