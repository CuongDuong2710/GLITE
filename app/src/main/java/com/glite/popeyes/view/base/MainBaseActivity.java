package com.glite.popeyes.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.glite.popeyes.PPYApplication;
import com.glite.popeyes.injector.components.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Brian
 * @date: 01/09/2016
 */

public abstract class MainBaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mUnbinder = ButterKnife.bind(this);

        injectComponent();
    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();

    }

    public abstract void injectComponent();

    public AppComponent getAppComponent() {
        return PPYApplication.get().getAppComponent();
    }

}

