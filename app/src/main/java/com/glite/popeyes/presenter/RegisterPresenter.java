package com.glite.popeyes.presenter;

import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.remote.reponse.authen.AuthenResponse;
import com.glite.popeyes.data.remote.request.auth.RegisterRequest;
import com.glite.popeyes.presenter.base.BasePresenter;
import com.glite.popeyes.util.Logger;
import com.glite.popeyes.view.register.RegisterContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author Tran Huy Phuc
 * @since 7/10/16
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private final DataRepository mDataRepository;

    @Inject
    public RegisterPresenter(RegisterContract.View registerView, DataRepository dataManager) {
        attachView(registerView);
        this.mDataRepository = dataManager;
    }

    @Override
    public void registerClick(RegisterRequest registerRequest) {
//        if (!getMvpView().validator()) {
//            return;
//        }
//
//        getMvpView().showLoadingDialog();
//        compositeSubscription.add(mDataRepository.register(registerRequest)
//                .subscribeOn(defaultSubscribeOn)
//                .observeOn(defaultObserveOn)
//                .subscribe(new Subscriber<AuthenResponse>() {
//                    @Override
//                    public void onCompleted() {
//                        getMvpView().hideLoadingDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getMvpView().showMessageError(e);
//                    }
//
//                    @Override
//                    public void onNext(AuthenResponse authenResponse) {
//                        if (authenResponse.getStatus()) {
//                            Member member = authenResponse.getData().getMember();
//                            if (member != null) {
//                                getMvpView().showRegisterSuccess();
//                            } else {
//                                getMvpView().showRegisterFail();
//                            }
//                        } else {
//                            getMvpView().showRegisterError(authenResponse.getError().getMsg());
//                        }
//                    }
//                }));

        registerAutoLogin(registerRequest);
    }

    private void registerAutoLogin(RegisterRequest registerRequest) {
        if (!getMvpView().validator()) {
            return;
        }

        getMvpView().showLoadingDialog();
        compositeSubscription.add(mDataRepository.registerAutoLogin(registerRequest)
                        .subscribeOn(defaultSubscribeOn)
                        .observeOn(defaultObserveOn)
                        .subscribe(new Subscriber<AuthenResponse>() {
                            @Override
                            public void onCompleted() {
                                getMvpView().hideLoadingDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getMvpView().hideLoadingDialog();
                                getMvpView().showMessageError(e);
                                Logger.debug(e.getMessage());
                            }

                            @Override
                            public void onNext(AuthenResponse authenResponse) {
                                Logger.enter();
                                if (authenResponse.getStatus()) {
                                    getMvpView().showRegisterSuccess();
                                } else {
                                    getMvpView().showRegisterError(authenResponse.getError().getMsg());
                                }
                            }
                        }));
    }
}
