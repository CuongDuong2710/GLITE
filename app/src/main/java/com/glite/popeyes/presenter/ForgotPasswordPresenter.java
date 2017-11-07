package com.glite.popeyes.presenter;

import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.request.forgot_password.ForgotPassRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.view.forgot_password.ForgotPasswordContract;

import javax.inject.Inject;

/**
 * @author Brian
 * @date: 16/09/2016
 */

public class ForgotPasswordPresenter extends BasePresenter<ForgotPasswordContract.View> implements ForgotPasswordContract.Presenter {

    private final DataRepository mDataRepository;

    @Inject
    ForgotPasswordPresenter(ForgotPasswordContract.View forgotPassView, DataRepository dataRepository) {
        attachView(forgotPassView);
        this.mDataRepository = dataRepository;
    }

    @Override
    public void submitPassword(ForgotPassRequest forgotPassRequest) {
        // TODO: impl request api here
    }
}
