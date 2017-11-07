package com.glite.popeyes.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;

import com.glite.popeyes.BuildConfig;
import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.request.auth.LoginRequest;
import com.glite.popeyes.data.remote.request.auth.SocialAuthRequest;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.activity.DaggerLoginComponent;
import com.glite.popeyes.injector.modules.activity.LoginModule;
import com.glite.popeyes.navigator.Navigator;
import com.glite.popeyes.presenter.LoginPresenter;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.Logger;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.custom.FuturaButton;
import com.glite.popeyes.view.main.MainActivity;
import com.glite.popeyes.view.base.MainBaseActivity;
import com.glite.popeyes.view.custom.CustomButtonIcon;
import com.glite.popeyes.view.custom.FuturaTextView;
import com.glite.popeyes.view.custom.VerdanaEditText;
import com.glitellp.libs.custom.LoadingDialog;
import com.glitellp.libs.utils.AlertUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends MainBaseActivity implements LoginContract.View, GoogleApiClient.OnConnectionFailedListener {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.btn_login_fb)
    CustomButtonIcon btnLoginFb;
    @BindView(R.id.btn_login_gplus)
    CustomButtonIcon btnLoginGplus;
    @BindView(R.id.btn_guest_login)
    FuturaButton ivGuestLogin;
    @BindView(R.id.text_forgot_password)
    TextView textForgotPass;
    @BindView(R.id.tv_click_here)
    FuturaTextView textClickHere;
    @BindView(R.id.tv_sign_up)
    FuturaTextView tvSignup;
    @BindView(R.id.text_email_address)
    VerdanaEditText textEmailAdd;
    @BindView(R.id.text_password)
    VerdanaEditText textPassword;
    @BindView(R.id.btn_login)
    FuturaButton btnLogin;

    @Inject
    LoginPresenter mPresenter;

    private boolean mHasPlayService;

    private LoadingDialog mLoadingDialog;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!CheckerUtil.isDeviceOnline()) {
            ToastUtil.showSingleToast(this, getString(R.string.error_no_internet_connection));
            return;
        }
        mHasPlayService = checkPlayServices();
        if (mHasPlayService) {

            // validate server_client_id from Google Developer Console
            if (validateServerClientId()) {
                setUpGoogleApiClient();

            } else {
                // show dialog invalid server client id
                showDialogError(getString(R.string.msg_dialog_invalid_server_id));
            }

        } else {
            // show dialog google play service not support
            showDialogError(getString(R.string.msg_google_play_service_not_support));
        }

        mLoadingDialog = new LoadingDialog(this, getString(R.string.msg_logging));
    }

    private boolean validateServerClientId() {
        String serverClientId = BuildConfig.SERVER_CLIENT_ID;
        String suffix = ".apps.googleusercontent.com";
        return serverClientId.trim().endsWith(suffix);
    }

    private void setUpGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(BuildConfig.SERVER_CLIENT_ID)
                .requestProfile()
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void showDialogError(String message) {
        AlertUtils.alertDialog(this, null, message,
                getString(R.string.ok), (dialog, which) -> dialog.dismiss());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void injectComponent() {
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(this)).build()
                .inject(this);
    }


    @OnClick(R.id.btn_guest_login)
    public void onGuestLoginClick() {
        Navigator.addFragmentToMainActivity(this, Constants.EXTRA_FROM.GUEST_FRAGMENT);
    }

    @OnClick(R.id.tv_sign_up)
    public void onSignUpClick() {
        MainActivity.start(this, Constants.EXTRA_FROM.ACCOUNT_REGISTER_FRAGMENT);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        String email = textEmailAdd.getText().toString();
        String password = textPassword.getText().toString();
        final LoginRequest loginRequest = LoginRequest.create(email, password, true);
        mPresenter.loginEmailClick(loginRequest);
    }

    @OnClick(R.id.btn_login_fb)
    public void connectWithFacebook() {
        if (!CheckerUtil.isDeviceOnline()) {
            ToastUtil.showSingleToast(this, getString(R.string.error_no_internet_connection));
        } else {
            mPresenter.loginFacebookClick(this);
        }
    }

    @OnClick(R.id.btn_login_gplus)
    public void loginGooglePlus() {
        if (!CheckerUtil.isDeviceOnline()) {
            ToastUtil.showSingleToast(this, getString(R.string.error_no_internet_connection));
        } else {
            if (!mHasPlayService) {
                showDialogError(getString(R.string.msg_google_play_service_not_support));
            } else {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        }

    }

    @OnClick(R.id.tv_click_here)
    public void onClickHere() {
        MainActivity.start(this, Constants.EXTRA_FROM.FORGOT_PASS_FRAGMENT);
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.showDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.hideDialog();
        }
    }

    @Override
    public boolean validator() {
        final CharSequence email = textEmailAdd.getText().toString();
        final CharSequence password = textPassword.getText().toString();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }

        return !TextUtils.isEmpty(password);
    }

    @Override
    public void showLoginFail() {
        showDialogError(getString(R.string.msg_login_fail));
    }

    @Override
    public void showLoginSuccess() {
        Navigator.addFragmentToMainActivity(this, Constants.EXTRA_FROM.HOME_DELIVERY_1_FRAGMENT);
    }

    @Override
    public void showLoginError(String error) {
        showDialogError(error);
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String error = ErrorMessageFactory.create(this, cause);
        ToastUtil.showSingleToast(this, error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                if (account != null) {
                    Logger.debug("TOKEN:", account.getIdToken());
                    handleSignInResult(account);
                }
            } else {
                ToastUtil.showSingleToast(this, "Authentication fail: " + String.valueOf(result.getStatus().getStatusCode()));
            }

        } else {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void handleSignInResult(GoogleSignInAccount account) {
        // Signed in successfully, show authenticated UI.
        SocialAuthRequest authRequest = new SocialAuthRequest();
        authRequest.provider = String.valueOf(Constants.LOGIN_PROVIDER.GPLUS);
        authRequest.token = account.getIdToken();
        authRequest.email = account.getEmail();
        authRequest.phone_number = "0974208829";
        authRequest.birthday = Constants.EMPTY;
        authRequest.picture = String.valueOf(account.getPhotoUrl());
        authRequest.gender = Constants.EMPTY;
        authRequest.first_name = account.getDisplayName();
        authRequest.last_name = account.getGivenName();

        Logger.debug("SocialRequest:", authRequest);

        // Request authentication api
        mPresenter.authGoogleSignIn(authRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Logger.error(connectionResult.getErrorMessage());
    }

}
