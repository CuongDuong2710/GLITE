package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.PostalCodeModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.home.HomeDeliveryFragment;

import dagger.Component;

/**
 * @author Brian
 * @date: 14/09/2016
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = PostalCodeModule.class)
public interface PostalCodeComponent {

    void inject(HomeDeliveryFragment homeDeliveryFragment);
}
