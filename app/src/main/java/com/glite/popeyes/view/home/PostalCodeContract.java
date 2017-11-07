package com.glite.popeyes.view.home;

import com.glite.popeyes.data.remote.request.postalcode.PostalCodeRequest;
import com.glite.popeyes.view.base.BaseMvpView;

/**
 * Created by PC on 9/5/2016.
 */
public interface PostalCodeContract {

    interface View extends BaseMvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showAvailablePostalCode(String streetName, String postalCode);

        void showMessageError(String error);

        void showNotAvailablePostalCode();

        void showOurStoresClosed();

        void showAddressDetailsNotFound();

        boolean validator();

    }

    interface Presenter {

        void checkPostalCode(PostalCodeRequest postalCodeRequest);
    }
}
