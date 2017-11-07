package com.glite.popeyes.exception;

import android.content.Context;

import com.glite.popeyes.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 04/08/2016.
 */
public final class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static String create(Context context, Throwable exception) {
        String error;
        if (exception instanceof UnknownHostException) {
            error = context.getString(R.string.error_no_internet_connection);
        } else if (exception instanceof SocketTimeoutException) {
            error = context.getString(R.string.error_timeout);
        } else if (exception instanceof ConnectException) {
            error = context.getString(R.string.error_connection_refused);
        } else {
            error = exception.getMessage();
        }
        return error;
    }

}
