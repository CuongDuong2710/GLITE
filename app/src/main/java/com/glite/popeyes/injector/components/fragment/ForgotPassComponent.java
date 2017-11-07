package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.ForgotPassModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.forgot_password.ForgotPasswordFragment;

import dagger.Component;

/**
 * @author Brian
 * @date: 16/09/2016
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ForgotPassModule.class)
public interface ForgotPassComponent {

    void inject(ForgotPasswordFragment forgotPasswordFragment);
}
