package com.glite.popeyes.injector.components.fragment;

import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.modules.fragment.ListOrderTimeModule;
import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.home.StartOrderFragment;

import dagger.Component;

/**
 * Created by PC on 9/16/2016.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = ListOrderTimeModule.class)
public interface ListOrderTimeComponent {

    void inject(StartOrderFragment startOrderFragment);
}
