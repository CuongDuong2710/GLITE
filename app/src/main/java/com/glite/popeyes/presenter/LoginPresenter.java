package com.glite.popeyes.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.reponse.authen.AuthenResponse;
import com.glite.popeyes.data.remote.reponse.authen.Member;
import com.glite.popeyes.data.remote.request.auth.LoginRequest;
import com.glite.popeyes.data.remote.request.auth.SocialAuthRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.Logger;
import com.glite.popeyes.view.login.LoginContract;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by tamhoang on 31/05/2016.
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private final int REQUEST_CODE_GPLUS = 1001;

    // Facebook callbackManger
    CallbackManager mCallbackManager;

    private final DataRepository mDataRepository;

    @Inject
    public LoginPresenter(LoginContract.View loginView, DataRepository dataManager) {
        attachView(loginView);
        this.mDataRepository = dataManager;
    }

    @Override
    public void loginEmailClick(LoginRequest loginRequest) {
        if (!getMvpView().validator()) {
            return;
        }

        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.login(loginRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<AuthenResponse>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoadingDialog();
                        getMvpView().showMessageError(e);
                    }

                    @Override
                    public void onNext(AuthenResponse authenResponse) {
                        if (authenResponse.getStatus()) {
                            Member member = authenResponse.getData().getMember();
                            if (member != null) {
                                getMvpView().showLoginSuccess();
                            } else {
                                getMvpView().showLoginFail();
                            }
                        } else {
                            getMvpView().showLoginError(authenResponse.getError().getMsg());
                        }
                    }
                }));


    }

    @Override
    public void loginFacebookClick(Activity activity) {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App <code>
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(), (object, response) -> {
                            if (response.getError() != null) {
                                getMvpView().showLoginError(response.getError().getErrorMessage());
                                return;
                            }

                            String first_name = object.optString("first_name");
                            String last_name = object.optString("last_name");
                            String email = object.optString("email");
                            String picture = object.optString("picture");
                            String gender = object.optString("gender");
                            String age_range = object.optString("age_range");
                            String birthday = object.optString("user_birthday");
                            Logger.debug("Birthday: ", birthday);

                            AccessToken accessToken = loginResult.getAccessToken();
                            final String accessFbToken = accessToken.getToken();

                            Logger.debug("FBToken:", accessFbToken);

                            String requestUrl = "https://graph.facebook.com/me?fields=address,mobile_phone&\n" +
                                    "access_token=" + accessFbToken;

                            SocialAuthRequest socialAuthRequest = new SocialAuthRequest();
                            socialAuthRequest.provider = String.valueOf(Constants.LOGIN_PROVIDER.FACEBOOK);
                            socialAuthRequest.token = accessFbToken;
                            socialAuthRequest.email = email;
                            socialAuthRequest.phone_number = "0974200829";
                            socialAuthRequest.birthday = age_range;
                            socialAuthRequest.first_name = first_name;
                            socialAuthRequest.last_name = last_name;
                            socialAuthRequest.picture = picture;
                            socialAuthRequest.gender = gender;

                            // TODO: authentication facebook with this access token
                            authSocial(socialAuthRequest);

                            AccessToken.setCurrentAccessToken(accessToken);
                        }
                );
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name, email, picture, gender, age_range");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Logger.debug("onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                getMvpView().showMessageError(error);
            }
        });

        List<String> permissionNeeds = Arrays.asList("public_profile", "email", "user_birthday", "user_friends");
        LoginManager.getInstance().logInWithReadPermissions(activity, permissionNeeds);
    }

    /**
     * Handle onActivityResult method
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void authSocial(SocialAuthRequest socialAuthRequest) {
        if (socialAuthRequest == null) {
            return;
        }

        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.authSocial(socialAuthRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<AuthenResponse>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoadingDialog();
                        getMvpView().showMessageError(e);
                    }

                    @Override
                    public void onNext(AuthenResponse authenResponse) {
                        if (authenResponse.getStatus()) {
                            getMvpView().showLoginSuccess();
                        } else {
                            getMvpView().showLoginError(authenResponse.getError().getMsg());
                        }
                    }
                }));
    }

    @Override
    public void authGoogleSignIn(SocialAuthRequest socialAuthRequest) {
        if (socialAuthRequest != null) {
            authSocial(socialAuthRequest);
        } else {
            getMvpView().showLoginFail();
        }
    }
}
