package com.glite.popeyes.view.home;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.request.postalcode.PostalCodeRequest;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerPostalCodeComponent;
import com.glite.popeyes.injector.modules.fragment.PostalCodeModule;
import com.glite.popeyes.presenter.PostalCodePresenter;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.VerdanaTextView;
import com.glite.popeyes.view.custom.dialog.CustomDialogPostalCode;
import com.glitellp.libs.custom.LoadingDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDeliveryFragment extends BaseFragment implements PostalCodeContract.View {

    @BindView(R.id.edit_postal_code_here)
    EditText mEditPostalCode;
    @BindView(R.id.text_our_delivery_time)
    VerdanaTextView mTextOurDeliveryTime;

    @Inject
    PostalCodePresenter mPostalCodePresenter;

    private LoadingDialog mLoadingDialog;

    public HomeDeliveryFragment() {
        // Required empty public constructor
    }

    public static HomeDeliveryFragment newInstance() {
        return new HomeDeliveryFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_home_delivery;
    }

    @Override
    public void injectComponent() {
        DaggerPostalCodeComponent.builder().appComponent(getAppComponent())
                .postalCodeModule(new PostalCodeModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingDialog = new LoadingDialog(getActivity(), getString(R.string.msg_checking_postal_code));
        mTextOurDeliveryTime.setTypeface(null, Typeface.ITALIC);
    }

    @Override
    public void onDestroyView() {
//        if (mPostalCodePresenter != null) {
//            mPostalCodePresenter.detach();
//        }
        super.onDestroyView();
    }

    @OnClick(R.id.btn_check_availability_postal_code)
    public void onCheckPostalCode() {
        String postalCode = CheckerUtil.trimmed(mEditPostalCode.getText().toString());
        PostalCodeRequest postalCodeRequest = new PostalCodeRequest(postalCode);
        mPostalCodePresenter.checkPostalCode(postalCodeRequest);
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
    public void showAvailablePostalCode(String streetName, String postalCode) {
        ConfirmDeliveryAddressFragment fragment = ConfirmDeliveryAddressFragment.newInstance(streetName, postalCode);
        replaceFragment(R.id.main_container, fragment);
    }

    @Override
    public void showMessageError(String error) {
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public void showNotAvailablePostalCode() {
        showWrongDialogPostalCode(getString(R.string.dialog_title_not_available),
                getString(R.string.dialog_message_not_available));
    }

    @Override
    public void showOurStoresClosed() {
        showWrongDialogPostalCode(getString(R.string.dialog_title_stores_closed),
                getString(R.string.dialog_message_stores_closed));
    }

    @Override
    public void showAddressDetailsNotFound() {
        ToastUtil.showSingleToast(getActivity(), "The location of this postal code is not found. Please try again!");
    }

    @Override
    public boolean validator() {
        String postalCode = mEditPostalCode.getText().toString();

        if (TextUtils.isEmpty(postalCode)) {
            ToastUtil.showSingleToast(getActivity(), getString(R.string.text_please_input_postal_code));
            return false;
        }
        return true;
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String error = ErrorMessageFactory.create(getActivity(), cause);
        ToastUtil.showSingleToast(getActivity(), error);
    }

    private void showWrongDialogPostalCode(String title, String message) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(message)) {
            return;
        }
        CustomDialogPostalCode dialogPostalCode = CustomDialogPostalCode.newInstance(title, message);
        dialogPostalCode.show(getChildFragmentManager(), "dialog");
    }
}
