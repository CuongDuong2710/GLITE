package com.glite.popeyes.view.forgot_password;

import com.glite.popeyes.data.remote.request.forgot_password.ForgotPassRequest;
import com.glite.popeyes.view.base.BaseMvpView;

/**
 * @author Brian
 * @date: 16/09/2016
 */

public interface ForgotPasswordContract {

    interface View extends BaseMvpView {

        boolean validator();

        void showResponseError(String msg);

        void showLoadingDialog();

        void hideLoadingDialog();
    }

    interface Presenter {

        void submitPassword(ForgotPassRequest forgotPassRequest);
    }
}
