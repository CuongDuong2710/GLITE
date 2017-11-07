package com.glite.popeyes.injector.modules.fragment;

import com.glite.popeyes.injector.scropes.FragmentScope;
import com.glite.popeyes.view.guest.GuestContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Brian
 * @date: 13/09/2016
 */

@Module
public class GuestModule {

    private final GuestContract.View guestView;

    public GuestModule(GuestContract.View guestView) {
        this.guestView = guestView;
    }

    @Provides
    @FragmentScope
    GuestContract.View provideGuestView() {
        return guestView;
    }

}
