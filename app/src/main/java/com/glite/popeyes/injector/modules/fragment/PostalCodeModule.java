package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.home.PostalCodeContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 14/09/2016
 */

@Module
public class PostalCodeModule {

    private final PostalCodeContract.View postalView;

    public PostalCodeModule(PostalCodeContract.View postalView) {
        this.postalView = postalView;
    }

    @Provides
    @FragmentScope
    PostalCodeContract.View providePostalView() {
        return postalView;
    }
}
