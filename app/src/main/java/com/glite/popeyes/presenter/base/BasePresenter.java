package com.glite.popeyes.presenter.base;

import com.glite.popeyes.exception.MvpViewNotAttachedException;
import com.glite.popeyes.view.base.BaseMvpView;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tamhoang on 31/05/2016.
 */
public abstract class BasePresenter<V extends BaseMvpView> implements Presenter {

    // We choose Scheduler.io() to running task in a thread pool
    protected final Scheduler defaultSubscribeOn = Schedulers.io();

    // Observe result in the main thread to be able to update UI
    protected final Scheduler defaultObserveOn = AndroidSchedulers.mainThread();

    protected final CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void attachView(V view) {
        this.mMvpView = view;
    }

    private V mMvpView;

    @Override
    public void detach() {
        mMvpView = null;
        unSubscribe();
    }

    private void unSubscribe() {
        if (compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    private boolean isViewAttached() {
        return mMvpView != null;
    }

    protected V getMvpView() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
        return mMvpView;
    }

}
