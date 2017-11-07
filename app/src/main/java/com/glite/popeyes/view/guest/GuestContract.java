package com.glite.popeyes.view.guest;

import android.content.Context;

import com.glite.popeyes.view.base.BaseMvpView;

/**
 * @author Brian
 * @date: 13/09/2016
 */

public interface GuestContract {

    interface View extends BaseMvpView {

        boolean validator();

        void showOrderNow();
    }

    interface Presenter {

        void summitOrderNowClick(Context context);
    }
}
