package com.glite.popeyes.presenter;

import android.content.Context;

import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.guest.GuestContract;

import javax.inject.Inject;

/**
 * @author Brian
 * @date: 13/09/2016
 */

public class GuestPresenter extends BasePresenter<GuestContract.View> implements GuestContract.Presenter {

    @Inject
    GuestPresenter(GuestContract.View guestView) {
        attachView(guestView);
    }

    @Override
    public void summitOrderNowClick(Context context) {
        // TODO: impl later
        ToastUtil.showSingleToast(context, "Submitted");
    }
}
