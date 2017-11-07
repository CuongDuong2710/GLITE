package com.glite.popeyes.presenter;

import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryResponse;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.MenuByCategoryResponse;
import com.glite.popeyes.data.remote.request.menu.CategoryIDRequest;
import com.glite.popeyes.data.remote.request.menu.ClientIDRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.view.menu.our_menu.OurMenuContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author Brian
 * @date: 16/09/2016
 */

public class OurMenuPresenter extends BasePresenter<OurMenuContract.View> implements OurMenuContract.Presenter {

    private final DataRepository mDataRepository;

    @Inject
    OurMenuPresenter(OurMenuContract.View ourView, DataRepository dataRepository) {
        attachView(ourView);
        this.mDataRepository = dataRepository;
    }

    @Override
    public void requestCategories(ClientIDRequest clientIDRequest) {
        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.getCategories(clientIDRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<CategoryResponse>() {
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
                    public void onNext(CategoryResponse categoryResponse) {
                        if (categoryResponse.getStatus()) {
                            getMvpView().populateCategory(categoryResponse.getData().getCategoryList());
                        } else {
                            getMvpView().showResponseError(categoryResponse.getError().getMsg());
                        }
                    }
                }));
    }

    @Override
    public void onItemClick(CategoryIDRequest categoryIDRequest) {
        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.getMenuByCategory(categoryIDRequest)
                .subscribeOn(defaultSubscribeOn)
                .observeOn(defaultObserveOn)
                .subscribe(new Subscriber<MenuByCategoryResponse>() {
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
                    public void onNext(MenuByCategoryResponse menuByCategoryResponse) {
                        if (menuByCategoryResponse.getStatus()) {
                            getMvpView().populateSubCategory(menuByCategoryResponse.getData().getMenu());
                        } else {
                            getMvpView().showResponseError(menuByCategoryResponse.getError().getMsg());
                        }
                    }
                }));
    }

    @Override
    public void clickLoadMoreCategory() {
        // TODO: call api load more
    }
}
