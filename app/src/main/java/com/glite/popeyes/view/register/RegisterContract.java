package com.glite.popeyes.view.register;

import com.glite.popeyes.data.remote.request.auth.RegisterRequest;
import com.glite.popeyes.view.base.BaseMvpView;

/**
 * @author Brian
 * @date: 01/09/2016
 */
public interface RegisterContract {

    interface View extends BaseMvpView {

        boolean validator();

        void showLoadingDialog();

        void hideLoadingDialog();

        void showRegisterFail();

        void showRegisterError(String error);

        void showRegisterSuccess();
    }


    interface Presenter {

        void registerClick(RegisterRequest registerRequest);
    }
}
