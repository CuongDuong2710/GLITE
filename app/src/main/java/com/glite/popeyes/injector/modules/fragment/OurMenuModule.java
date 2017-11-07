package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.menu.our_menu.OurMenuContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 16/09/2016
 */

@Module
public class OurMenuModule {

    private final OurMenuContract.View mOurMenuView;

    public OurMenuModule(OurMenuContract.View ourMenuView) {
        this.mOurMenuView = ourMenuView;
    }

    @Provides
    @FragmentScope
    OurMenuContract.View provideOurMenuView() {
        return mOurMenuView;
    }
}
