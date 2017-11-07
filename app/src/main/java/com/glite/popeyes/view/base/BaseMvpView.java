package com.glite.popeyes.view.base;

/**
 * Created by tamhoang on 31/05/2016.
 *
 * Base interface that any class wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface BaseMvpView {

    void showMessageError(Throwable cause);

}
