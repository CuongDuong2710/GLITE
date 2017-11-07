package com.glite.popeyes.view.menu.cart;


import android.support.v4.app.Fragment;

import com.glite.popeyes.R;
import com.glite.popeyes.view.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment {


    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void injectComponent() {

    }
}
