package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.register.RegisterContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 01/09/2016
 */

@Module
public class RegisterModule {

    private final RegisterContract.View mRegisterView;

    public RegisterModule(RegisterContract.View registerView) {
        this.mRegisterView = registerView;
    }

    @Provides
    @FragmentScope
    RegisterContract.View provideRegisterView() {
        return mRegisterView;
    }

}
