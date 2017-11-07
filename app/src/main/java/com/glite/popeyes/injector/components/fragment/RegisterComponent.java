package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.RegisterModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.register.RegisterAccountFragment;

import dagger.Component;

/**
 * @author Brian
 * @date: 01/09/2016
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {

    void inject(RegisterAccountFragment registerAccountFragment);
}
