package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.listordertime.ListOrderTimeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PC on 9/16/2016.
 */
@Module
public class ListOrderTimeModule {

    private final ListOrderTimeContract.View view;

    public ListOrderTimeModule(ListOrderTimeContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    ListOrderTimeContract.View provideListOrderTimeView() {
        return view;
    }
}
