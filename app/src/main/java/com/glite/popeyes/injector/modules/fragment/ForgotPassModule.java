package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.forgot_password.ForgotPasswordContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 16/09/2016
 */

@Module
public class ForgotPassModule {

    private final ForgotPasswordContract.View forgotPassView;

    public ForgotPassModule(ForgotPasswordContract.View forgotPassView) {
        this.forgotPassView = forgotPassView;
    }

    @Provides
    @FragmentScope
    ForgotPasswordContract.View provideForgotView() {
        return forgotPassView;
    }
}
