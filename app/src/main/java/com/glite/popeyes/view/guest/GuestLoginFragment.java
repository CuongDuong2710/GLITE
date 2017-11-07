package com.glite.popeyes.view.guest;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.EditText;

import com.glite.popeyes.R;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerGuestComponent;
import com.glite.popeyes.injector.modules.fragment.GuestModule;
import com.glite.popeyes.presenter.GuestPresenter;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.FuturaButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestLoginFragment extends BaseFragment implements GuestContract.View {

    @BindView(R.id.edt_guest_name)
    EditText edtGuestName;

    @BindView(R.id.edt_guest_email)
    EditText edtGuestEmail;

    @BindView(R.id.edt_guest_phone)
    EditText edtGuestPhone;

    @BindView(R.id.btn_submit_to_order)
    FuturaButton mSubmitButton;

    @Inject
    GuestPresenter mGuestPresenter;

    public GuestLoginFragment() {
        // Required empty public constructor
    }

    public static GuestLoginFragment newInstance() {
        return new GuestLoginFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_guest_login;
    }

    @Override
    public void injectComponent() {
        DaggerGuestComponent.builder().appComponent(getAppComponent())
                .guestModule(new GuestModule(this))
                .build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSubmitButton.setElevation(8);
        } else {
            ViewCompat.setElevation(mSubmitButton, 8);
        }

    }

    @Override
    public boolean validator() {
        // TODO: handle later
        return true;
    }

    @Override
    public void showOrderNow() {
        ToastUtil.showSingleToast(getAttachContext(), "Show OrderNow");
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String message = ErrorMessageFactory.create(getAttachContext(), cause);
        ToastUtil.showSingleToast(getAttachContext(), message);
    }

    @OnClick(R.id.btn_submit_to_order)
    public void submitToOrderClick() {
        mGuestPresenter.summitOrderNowClick(getAttachContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mGuestPresenter != null) {
            mGuestPresenter.detach();
        }
    }
}
