package com.glite.popeyes.view.login;

import android.app.Activity;

import com.glite.popeyes.data.remote.request.auth.LoginRequest;
import com.glite.popeyes.data.remote.request.auth.SocialAuthRequest;
import com.glite.popeyes.view.base.BaseMvpView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by tamhoang on 31/05/2016.
 */
public interface LoginContract {

    interface View extends BaseMvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        boolean validator();

        void handleSignInResult(GoogleSignInAccount result);

        void showLoginFail();

        void showLoginSuccess();

        void showLoginError(String error);

    }

    interface Presenter {

        void loginEmailClick(LoginRequest loginRequest);

        void loginFacebookClick(Activity activity);

        void authGoogleSignIn(SocialAuthRequest socialAuthRequest);

    }

}
