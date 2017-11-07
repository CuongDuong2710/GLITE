package com.glite.popeyes.view.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.request.auth.RegisterRequest;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerRegisterComponent;
import com.glite.popeyes.injector.modules.fragment.RegisterModule;
import com.glite.popeyes.navigator.Navigator;
import com.glite.popeyes.presenter.RegisterPresenter;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.CustomAsteriskPassword;
import com.glitellp.libs.custom.LoadingDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAccountFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.text_provide_registration)
    TextView textProvideRegistration;
    @BindView(R.id.text_first_name)
    TextView textFirstName;
    @BindView(R.id.edit_first_name)
    EditText editFirstName;

    @BindView(R.id.text_last_name)
    TextView textLastName;
    @BindView(R.id.edit_last_name)
    EditText editLastName;

    @BindView(R.id.text_register_dob)
    TextView texRegisterDOB;

    @BindView(R.id.edt_register_day)
    EditText edtRegisterDay;

    @BindView(R.id.edt_register_month)
    EditText edtRegisterMonth;

    @BindView(R.id.edt_register_year)
    EditText edtRegisterYear;

    @BindView(R.id.text_register_email)
    TextView textRegisterEmail;
    @BindView(R.id.edt_register_email)
    EditText edtRegisterEmail;

    @BindView(R.id.text_register_phone)
    TextView textRegisterPhone;
    @BindView(R.id.edt_register_phone)
    EditText edtRegisterPhone;

    @BindView(R.id.edt_register_gender)
    EditText edtRegisterGender;

    @BindView(R.id.text_register_pass)
    TextView textRegisterPass;
    @BindView(R.id.edt_register_pass)
    EditText edtRegisterPass;
    @BindView(R.id.text_register_retype_pass)
    TextView textRegisterRePass;
    @BindView(R.id.edt_register_retype_pass)
    EditText edtRegisterRetypePass;
    @BindView(R.id.button_register_now)
    Button btnRegisterNow;

    @BindView(R.id.button_checkbox_agree_receive_mail)
    CheckBox btnAgree;
    @BindView(R.id.button_checkbox_do_not_agree_receive_mail)
    CheckBox btnDoNotAgree;

    @Inject
    RegisterPresenter mRegisterPresenter;

    private LoadingDialog mLoadingDialog;

    public RegisterAccountFragment() {
        // Required empty public constructor
    }

    public static RegisterAccountFragment newInstance() {
        return new RegisterAccountFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_account_registration;
    }

    @Override
    public void injectComponent() {
        DaggerRegisterComponent.builder().appComponent(getAppComponent())
                .registerModule(new RegisterModule(this))
                .build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingDialog = new LoadingDialog(getActivity(), getString(R.string.msg_register));
        btnAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline));
        btnDoNotAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline_blank));

//        // Transform to asterisk password
        edtRegisterPass.setTransformationMethod(new CustomAsteriskPassword());
        edtRegisterRetypePass.setTransformationMethod(new CustomAsteriskPassword());

        // CheckBox listener
        onCheckedChangeListener();
    }

    @OnClick(R.id.button_register_now)
    public void onRegisterClick() {
        String firstName = CheckerUtil.trimmed(editFirstName.getText().toString());
        String lastName = CheckerUtil.trimmed(editLastName.getText().toString());
        String email = CheckerUtil.trimmed(edtRegisterEmail.getText().toString());
        String password = CheckerUtil.trimmed(edtRegisterPass.getText().toString());
        String phoneNumber = CheckerUtil.trimmed(edtRegisterPhone.getText().toString());
        String gender = CheckerUtil.trimmed(edtRegisterGender.getText().toString());

        final String day = CheckerUtil.trimmed(edtRegisterDay.getText().toString());
        final String month = CheckerUtil.trimmed(edtRegisterMonth.getText().toString());
        final String year = CheckerUtil.trimmed(edtRegisterYear.getText().toString());
        final String dob = year + "-" + month + "-" + day;

        final RegisterRequest registerRequest = RegisterRequest.create(
                email, password, firstName, lastName, gender, dob, phoneNumber);
        mRegisterPresenter.registerClick(registerRequest);

    }


    @Override
    public boolean validator() {
        // FIXME: validation the register fields before call api register
        return true;
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
    public void showRegisterFail() {
        ToastUtil.showSingleToast(getActivity(), "Registration failed. Please try again!");
    }

    @Override
    public void showRegisterError(String error) {
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public void showRegisterSuccess() {
//        Navigator.addFragmentToMainActivity(getActivity(), Constants.EXTRA_FROM.HOME_DELIVERY_1_FRAGMENT);
        ToastUtil.showSingleToast(getActivity(), "Success");
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String error = ErrorMessageFactory.create(getActivity(), cause);
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRegisterPresenter != null) {
            mRegisterPresenter.detach();
        }
    }

    /**
     * CheckBox listener
     */
    private void onCheckedChangeListener() {

        btnAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    btnAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline));
                    btnDoNotAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline_blank));
                    btnDoNotAgree.setChecked(false);
                } else {
                    btnAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline_blank));
                    btnAgree.setChecked(false);
                }
            }
        });

        btnDoNotAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    btnDoNotAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline));
                    btnAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline_blank));
                    btnAgree.setChecked(false);
                } else {
                    btnDoNotAgree.setButtonDrawable(getResources().getDrawable(R.drawable.check_box_outline_blank));
                    btnDoNotAgree.setChecked(false);
                }
            }
        });
    }
}
