package com.glite.popeyes.view.menu.checkout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.font.FontManager;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.dialog.DialogEnterPin;
import com.glite.popeyes.view.menu.AuthenPinActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment extends BaseFragment implements DialogEnterPin.OnEnterPinClickListener {

    @BindView(R.id.text_delivery_address_value)
    TextView mTxtDeliveryAddressValue;
    @BindView(R.id.text_phone_number_label)
    TextView mTxtPhoneNumberLabel;
    @BindView(R.id.text_phone_number_value)
    TextView mTxtPhoneNumberValue;
    @BindView(R.id.text_delivery_label)
    TextView mTxtDeliveryLabel;
    @BindView(R.id.text_delivery_date)
    TextView mTxtDeliveryDate;
    @BindView(R.id.delivery_selected_time)
    TextView mTxtDeliverySelectedTime;
    @BindView(R.id.delivery_selected_time_value)
    TextView mTxtDeliverySelectedTimeValue;
    @BindView(R.id.text_total)
    TextView mTxtTotal;
    @BindView(R.id.total_value)
    TextView mTxtTotalValue;
    @BindView(R.id.buy_with_master_pass)
    ImageButton mBtnBuyMasterPass;
//    @BindView(R.id.layout_key_pad)
//    LinearLayout mLayoutKeyPad;

    EditText edtPass;

    private LayoutInflater inflater;
    private String mPasswordString;

    public CheckOutFragment() {
        // Required empty public constructor
    }

    public static CheckOutFragment newInstance() {
        CheckOutFragment fragment = new CheckOutFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inflater = getActivity().getLayoutInflater();

        setTypeFace();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_checkout;
    }

    @Override
    public void injectComponent() {

    }

    @OnClick(R.id.buy_with_master_pass)
    public void onBuyMasterPassClicked() {

        showDialogEnterPin();
    }

    private DialogEnterPin dialogEnterPin;

    public void showDialogEnterPin() {

        dialogEnterPin = DialogEnterPin.newInstance();
        dialogEnterPin.show(getChildFragmentManager(), "ENTER_PIN");

        dialogEnterPin.setOnEnterPinClickListener(this);
    }

    @Override
    public void onEnterPinClicked() {

        getContext().startActivity(AuthenPinActivity.getStartIntent(getContext()));

        dialogEnterPin.dismiss();
    }

    private void setTypeFace() {
        mTxtPhoneNumberLabel.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtPhoneNumberValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtDeliveryLabel.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtDeliveryDate.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtDeliverySelectedTime.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtDeliverySelectedTimeValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtTotal.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
        mTxtTotalValue.setTypeface(FontManager.getFuturaStdBoldCondensedFont(getContext()));
    }


}
