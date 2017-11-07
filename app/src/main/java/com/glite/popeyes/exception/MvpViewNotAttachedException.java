package com.glite.popeyes.exception;

/**
 * Created by tamhoang on 31/05/2016.
 */
public class MvpViewNotAttachedException extends RuntimeException {

    public MvpViewNotAttachedException() {
        super("Please call Presenter.attachView(BaseView) before" +
                " requesting data to the Presenter");
    }
}
