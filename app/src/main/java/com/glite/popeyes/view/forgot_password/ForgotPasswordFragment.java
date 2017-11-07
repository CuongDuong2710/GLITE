package com.glite.popeyes.view.forgot_password;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.glite.popeyes.R;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerForgotPassComponent;
import com.glite.popeyes.injector.modules.fragment.ForgotPassModule;
import com.glite.popeyes.presenter.ForgotPasswordPresenter;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glitellp.libs.custom.LoadingDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends BaseFragment implements ForgotPasswordContract.View {

    @BindView(R.id.edit_new_password)
    EditText mEditNewPassword;
    @BindView(R.id.edit_confirm_new_password)
    EditText mEditConfirmNewPassword;

    @Inject
    ForgotPasswordPresenter mForgotPasswordPresenter;

    private LoadingDialog mLoadingDialog;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public void injectComponent() {
        DaggerForgotPassComponent.builder().appComponent(getAppComponent())
                .forgotPassModule(new ForgotPassModule(this))
                .build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingDialog = new LoadingDialog(getActivity(), getString(R.string.msg_dialog_loading_forgot_pass));
    }

    @OnClick(R.id.btn_save_new_password_now)
    public void submitPassword() {
        // TODO: impl later
    }

    @OnClick(R.id.btn_cancel_new_password)
    public void cancelPassword() {
        getActivity().finish();
    }

    @Override
    public boolean validator() {
        return false;
    }

    @Override
    public void showResponseError(String msg) {
        ToastUtil.showSingleToast(getActivity(), msg);
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
    public void showMessageError(Throwable cause) {
        final String error = ErrorMessageFactory.create(getActivity(), cause);
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mForgotPasswordPresenter != null) {
            mForgotPasswordPresenter.detach();
        }
    }
}
