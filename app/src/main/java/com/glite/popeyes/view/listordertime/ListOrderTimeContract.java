package com.glite.popeyes.view.listordertime;

import com.glite.popeyes.data.remote.reponse.listordertime.Item;
import com.glite.popeyes.data.remote.request.listordertime.ListOrderTimeRequest;
import com.glite.popeyes.view.base.BaseMvpView;

import java.util.List;

/**
 * Created by PC on 9/16/2016.
 */
public interface ListOrderTimeContract {

    interface View extends BaseMvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showDatePicker();

        void showTimePicker();

        void showMessageError(String error);

        boolean validator();

    }

    interface Presenter {

        List<Item> getListOrderTime(ListOrderTimeRequest listOrderTimeRequest);
    }
}
