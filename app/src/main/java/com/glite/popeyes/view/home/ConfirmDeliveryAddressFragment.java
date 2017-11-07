package com.glite.popeyes.view.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.glite.popeyes.R;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmDeliveryAddressFragment extends BaseFragment {

    private final static String ARG_STREET_ADDRESS = "arg_street_address";
    private final static String ARG_POSTAL_CODE = "arg_postal_code";


    @BindView(R.id.edt_confirm_delivery_address)
    EditText mEditStreetAddress;
    @BindView(R.id.edt_confirm_delivery_address2)
    EditText mEditAddress2;
    @BindView(R.id.edt_confirm_unit_no)
    EditText mEditUnitNo;
    @BindView(R.id.edt_confirm_contact_number)
    EditText mEditContactNumber;
    @BindView(R.id.edt_confirm_delivery_city)
    EditText mEditDeliveryCity;
    @BindView(R.id.edt_confirm_delivery_postal_code)
    EditText mEditDeliveryPostalCode;

    private String mStreetAddress;
    private String mPostalCode;

    public ConfirmDeliveryAddressFragment() {
        // Required empty public constructor
    }

    public static ConfirmDeliveryAddressFragment newInstance(String streetAddress, String postalCode) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_STREET_ADDRESS, streetAddress);
        bundle.putString(ARG_POSTAL_CODE, postalCode);
        ConfirmDeliveryAddressFragment fragment = new ConfirmDeliveryAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStreetAddress = getArguments().getString(ARG_STREET_ADDRESS);
        mPostalCode = getArguments().getString(ARG_POSTAL_CODE);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_confirm_delivery_address;
    }

    @Override
    public void injectComponent() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditStreetAddress.setText(CheckerUtil.checkIfNull(mStreetAddress));
        mEditDeliveryPostalCode.setText(CheckerUtil.checkIfNull(mPostalCode));

    }

    @OnClick(R.id.btn_confirm_delivery_address)
    public void confirmDeliveryAddressClick() {
        final String deliveryAddress = CheckerUtil.checkIfNull(mEditStreetAddress.getText().toString());
        final String deliveryCity = CheckerUtil.checkIfNull(mEditDeliveryCity.getText().toString());
        final String deliveryPostalCode = CheckerUtil.checkIfNull(mEditDeliveryPostalCode.getText().toString());
        StartOrderFragment startOrderFragment = StartOrderFragment.newInstance(deliveryAddress, deliveryCity, deliveryPostalCode);
        replaceFragment(R.id.main_container, startOrderFragment);

    }

}
