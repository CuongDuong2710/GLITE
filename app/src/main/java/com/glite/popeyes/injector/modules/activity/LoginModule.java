package com.glite.popeyes.injector.modules.activity;

import com.glite.popeyes.injector.scropes.ActivityScope;
import com.glite.popeyes.view.login.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 31/08/2016
 */

@Module
public class LoginModule {

    private final LoginContract.View mLoginView;

    public LoginModule(LoginContract.View loginView) {
        this.mLoginView = loginView;
    }

    @Provides
    @ActivityScope
    LoginContract.View provideLoginView() {
        return mLoginView;
    }

}
