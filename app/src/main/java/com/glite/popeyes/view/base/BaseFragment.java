package com.glite.popeyes.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.glite.popeyes.R;
import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.view.menu.our_menu.OurMenuFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HP on 24/05/2016.
 */

public abstract class BaseFragment extends Fragment {

    private Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public Context getAttachContext() {
        return mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;

    }

    public abstract int getLayoutResourceId();

    public abstract void injectComponent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public AppComponent getAppComponent() {
        return ((MainBaseActivity) getActivity()).getAppComponent();
    }

    protected void replaceChildFragment(int containerId, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(containerId, fragment)
                .addToBackStack(fragment.getClass().getName()).commit();
    }

    protected void replaceFragment(int containerId, Fragment fragment) {
        getFragmentManager().beginTransaction().add(containerId, fragment)
                .addToBackStack(fragment.getClass().getName()).commit();
    }

    protected void replaceFragment(int containerId, Fragment fragment, String TAG) {
        getFragmentManager().beginTransaction().add(containerId, fragment)
                .addToBackStack(TAG).commit();
    }
}
