package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.GuestModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.guest.GuestLoginFragment;

import dagger.Component;

/**
 * @author Brian
 * @date: 13/09/2016
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = GuestModule.class)
public interface GuestComponent {

    void inject(GuestLoginFragment guestLoginFragment);
}
