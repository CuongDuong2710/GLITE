package com.glite.popeyes.view.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.glite.popeyes.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 18/05/2016.
 */

public abstract class ToolBarBaseActivity extends MainBaseActivity {

    @BindView(R.id.icon_navigation_back)
    ImageView mNavigationBack;
    @BindView(R.id.icon_navigation_drawer)
    ImageView mNavigationDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpToolbar();
    }

    @OnClick(R.id.icon_navigation_back)
    public void navigateBackClick() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }


    public abstract void setUpToolbar();

    public void showNavigationBack(boolean isShow) {
        if (mNavigationBack != null) {
            if (isShow) {
                mNavigationBack.setVisibility(View.VISIBLE);
            } else {
                mNavigationBack.setVisibility(View.GONE);
            }
        }
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragmentTag)
                .add(containerViewId, fragment, fragmentTag)
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}
