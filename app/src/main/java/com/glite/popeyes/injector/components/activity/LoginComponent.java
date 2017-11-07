package com.glite.popeyes.injector.components.activity;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.activity.LoginModule;
import com.glite.popeyes.injector.scropes.ActivityScope;
import com.glite.popeyes.view.login.LoginActivity;

import dagger.Component;

/**
 * @author Brian
 * @date: 01/09/2016
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
