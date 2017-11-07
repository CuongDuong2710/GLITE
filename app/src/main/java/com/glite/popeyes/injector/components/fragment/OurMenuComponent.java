package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.OurMenuModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.menu.our_menu.OurMenuFragment;

import dagger.Component;

/**
 * @author Brian
 * @date: 16/09/2016
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = OurMenuModule.class)
public interface OurMenuComponent {

    void inject(OurMenuFragment ourMenuFragment);
}
