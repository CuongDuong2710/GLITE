package com.glite.popeyes.view.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.reponse.listordertime.Date;
import com.glite.popeyes.data.remote.reponse.listordertime.Item;
import com.glite.popeyes.data.remote.reponse.listordertime.Time;
import com.glite.popeyes.data.remote.request.listordertime.ListOrderTimeRequest;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerListOrderTimeComponent;
import com.glite.popeyes.injector.modules.fragment.ListOrderTimeModule;
import com.glite.popeyes.presenter.ListOrderTimePresenter;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.custom.VerdanaTextView;
import com.glite.popeyes.view.custom.datepicker.SlideDateListener;
import com.glite.popeyes.view.custom.datepicker.SlideDatePicker;
import com.glite.popeyes.view.custom.timepicker.SlideTimeListener;
import com.glite.popeyes.view.custom.timepicker.SlideTimePicker;
import com.glite.popeyes.view.listordertime.ListOrderTimeContract;
import com.glite.popeyes.view.menu.DeliveryMenuActivity;
import com.glite.popeyes.view.menu.DeliveryMenuFragment;
import com.glitellp.libs.custom.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartOrderFragment extends BaseFragment implements ListOrderTimeContract.View{

    private static final String ARG_DELIVERY_ADDRESS = "arg_street_address";
    private static final String ARG_DELIVERY_CITY = "arg_city";
    private static final String ARG_DELIVERY_POSTAL_CODE = "arg_delivery_postal_code";

    @BindView(R.id.text_available_delivery_address)
    VerdanaTextView mTextAvailabeAdrees;
    @BindView(R.id.text_select_order_date)
    TextView mTextSelectOrderDate;
    @BindView(R.id.text_select_order_time)
    TextView mTextSelectOrderTime;

    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryPostalCode;

    private LoadingDialog mLoadingDialog;

    private List<Item> itemList = new ArrayList<>();
    private HashMap<Date, List<Time>> dataDateTime = new HashMap<>();

    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("EEE, MMMM dd, yyyy");
    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("h:mm a");

    private SlideDateListener dateListener = new SlideDateListener() {

        @Override
        public void onDateSet(java.util.Date date) {
            mTextSelectOrderDate.setText(mDateFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateCancel()
        {

        }
    };

    private SlideTimeListener timeListener = new SlideTimeListener() {

        @Override
        public void onTimeSet(java.util.Date date) {
            mTextSelectOrderTime.setText(mTimeFormatter.format(date.getTime()));
        }

        @Override
        public void onTimeCancel() {
            super.onTimeCancel();
        }
    };

    @Inject
    ListOrderTimePresenter mListOrderTimePresenter;

    public StartOrderFragment() {
        // Required empty public constructor
    }

    public static StartOrderFragment newInstance(String deliveryAddress, String deliveryCity, String deliveryPostalCode) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_DELIVERY_ADDRESS, deliveryAddress);
        bundle.putString(ARG_DELIVERY_CITY, deliveryCity);
        bundle.putString(ARG_DELIVERY_POSTAL_CODE, deliveryPostalCode);
        StartOrderFragment startOrderFragment = new StartOrderFragment();
        startOrderFragment.setArguments(bundle);
        return startOrderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deliveryAddress = getArguments().getString(ARG_DELIVERY_ADDRESS);
        deliveryCity = getArguments().getString(ARG_DELIVERY_CITY);
        deliveryPostalCode = (String) getArguments().get(ARG_DELIVERY_POSTAL_CODE);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_start_order_delivery;
    }

    @Override
    public void injectComponent() {
        DaggerListOrderTimeComponent.builder().appComponent(getAppComponent())
                .listOrderTimeModule(new ListOrderTimeModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String availableAddress = CheckerUtil.checkIfNull(deliveryAddress)
                + " " + CheckerUtil.checkIfNull(deliveryCity)
                + " " + CheckerUtil.checkIfNull(deliveryPostalCode);

        mTextAvailabeAdrees.setText(availableAddress);

        mLoadingDialog = new LoadingDialog(getActivity(), getResources().getString(R.string.msg_checking_postal_code));

        // TODO: request api
        ListOrderTimeRequest listOrderTimeRequest = new ListOrderTimeRequest(deliveryPostalCode);
        itemList = mListOrderTimePresenter.getListOrderTime(listOrderTimeRequest);
        getData();
    }

    @OnClick(R.id.button_order_asap)
    public void orderAsSoonAsPossible() {
        // FIXME: validate datetime order
    }

    @OnClick(R.id.button_order_later)
    public void orderLater() {
        // FIXME: show datetime view
    }

    @OnClick(R.id.button_start_order)
    public void startOrder() {
//        DeliveryMenuFragment menuFragment = DeliveryMenuFragment.newInstance();
//        replaceFragment(R.id.main_container, menuFragment);
        getContext().startActivity(DeliveryMenuActivity.getStartIntent(getContext()));
    }

    @OnClick(R.id.text_select_order_date)
    public void onDateOrderChoose() {
        showDatePicker();
    }

    @OnClick(R.id.text_select_order_time)
    public void onTimeOrderChoose() {
        showTimePicker();
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
    public void showDatePicker() {
        new SlideDatePicker.Builder(getFragmentManager())
                .setListener(dateListener)
                .setInitialDate(new java.util.Date())
                .build()
                .show();
    }

    @Override
    public void showTimePicker() {
        new SlideTimePicker.Builder(getFragmentManager())
                .setListener(timeListener)
                .setInitialDate(new java.util.Date())
                .build()
                .show();
    }

    @Override
    public void showMessageError(String error) {
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public boolean validator() {
        return false;
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String errorMessage = ErrorMessageFactory.create(getActivity(), cause);
        ToastUtil.showSingleToast(getActivity(), errorMessage);
    }

    private void getData() {

        for (Item item : itemList) {
            Date date = item.getDate();
            List<Time> timeList = item.getTimes();
            dataDateTime.put(date, timeList);
        }

    }
}
