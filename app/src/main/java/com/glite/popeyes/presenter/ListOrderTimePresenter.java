package com.glite.popeyes.presenter;

import android.util.Log;

import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.reponse.listordertime.Item;
import com.glite.popeyes.data.remote.reponse.listordertime.ListOrderTimeResponse;
import com.glite.popeyes.data.remote.reponse.listordertime.Outlet;
import com.glite.popeyes.data.remote.reponse.listordertime.Time;
import com.glite.popeyes.data.remote.request.listordertime.ListOrderTimeRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.view.listordertime.ListOrderTimeContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by PC on 9/16/2016.
 */
public class ListOrderTimePresenter extends BasePresenter<ListOrderTimeContract.View> implements ListOrderTimeContract.Presenter  {

    private final DataRepository mDataRepository;
    private List<Item> itemList = new ArrayList<>();

    @Inject
    public ListOrderTimePresenter(ListOrderTimeContract.View view, DataRepository mDataRepository) {
        attachView(view);
        this.mDataRepository = mDataRepository;
    }

    @Override
    public List<Item> getListOrderTime(ListOrderTimeRequest listOrderTimeRequest) {

//        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.getListOrderTime(listOrderTimeRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<ListOrderTimeResponse>() {
                    @Override
                    public void onCompleted() {
//                        getMvpView().hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        getMvpView().hideLoadingDialog();
//                        getMvpView().showMessageError(e);
                    }

                    @Override
                    public void onNext(ListOrderTimeResponse listOrderTimeResponse) {
                        if (listOrderTimeResponse.getStatus()) {
                            Outlet outlet = listOrderTimeResponse.getData().getOutlet();
                            if (outlet != null) {
                                itemList = outlet.getItems();
                            }
                        } else {
//                            getMvpView().showMessageError(listOrderTimeResponse.getError().getMsg());
                        }
                    }
                }));
        return itemList;
    }
}
