package com.glite.popeyes.presenter;

import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.reponse.postalcode.AddressDetails;
import com.glite.popeyes.data.remote.reponse.postalcode.PostalCodeResponse;
import com.glite.popeyes.data.remote.request.postalcode.PostalCodeRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.view.home.PostalCodeContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by PC on 9/5/2016.
 */
public class PostalCodePresenter extends BasePresenter<PostalCodeContract.View> implements PostalCodeContract.Presenter {

    private final DataRepository mDataRepository;

    @Inject
    public PostalCodePresenter(PostalCodeContract.View postalView, DataRepository dataManager) {
        attachView(postalView);
        this.mDataRepository = dataManager;
    }

    @Override
    public void checkPostalCode(PostalCodeRequest postalCodeRequest) {
        if (!getMvpView().validator()) {
            return;
        }

        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.checkPostalCode(postalCodeRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<PostalCodeResponse>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoadingDialog();
                        getMvpView().showMessageError(e);
                    }

                    @Override
                    public void onNext(PostalCodeResponse postalCodeResponse) {
                        if (postalCodeResponse.getStatus()) {
                            AddressDetails addressDetails = postalCodeResponse.getPostalCodeData().getAddressDetails();
                            if (addressDetails != null) {

                                final String found = addressDetails.getFound().trim();
                                if (checkResultCode(found) == Constants.FOUND.OK) {
                                    getMvpView().showAvailablePostalCode(addressDetails.getStreetName(),
                                            addressDetails.getPostalCode());

                                } else if (checkResultCode(found) == Constants.FOUND.NOT_AVAILABLE) {
                                    getMvpView().showNotAvailablePostalCode();

                                } else if (checkResultCode(found) == Constants.FOUND.STORES_CLOSED) {
                                    getMvpView().showOurStoresClosed();
                                }
                            } else {
                                getMvpView().showAddressDetailsNotFound();
                            }
                        } else {
                            getMvpView().showMessageError(postalCodeResponse.getError().getMsg());
                        }
                    }
                }));
    }

    /**
     * Check result code
     * if return code 200 is everything ok
     * if return code 404 delivery not available
     * if return code 303 stores closed
     *
     * @param found
     * @return code
     */
    private int checkResultCode(String found) {
        int foundInt = Integer.parseInt(found);
        if (foundInt == Constants.FOUND.OK) {
            return Constants.FOUND.OK;
        } else if (foundInt == Constants.FOUND.NOT_AVAILABLE) {
            return Constants.FOUND.NOT_AVAILABLE;
        } else if (foundInt == Constants.FOUND.STORES_CLOSED) {
            return Constants.FOUND.STORES_CLOSED;
        } else {
            return -1;
        }
    }
}
