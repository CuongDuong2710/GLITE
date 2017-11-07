package com.glite.popeyes.data.remote.request.auth;

import android.text.TextUtils;

import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.EncryptUtils;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public final class LoginRequest {

    public String email;
    public String password;

    public LoginRequest(String email, String password, boolean hasEncrypt) {
        this.email = email;
        if (hasEncrypt) {
            // encode password as a SHA1 before send
            if (!TextUtils.isEmpty(password)) {
                this.password = EncryptUtils.sha1(password);
            } else {
                this.password = Constants.EMPTY;
            }
        } else {
            this.password = password;
        }
    }

    public static LoginRequest create(String email, String password, boolean hasEncrypt) {
        return new LoginRequest(email, password, hasEncrypt);
    }
}
