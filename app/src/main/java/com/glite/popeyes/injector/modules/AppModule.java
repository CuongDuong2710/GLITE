package com.glite.popeyes.injector.modules;

import com.glite.popeyes.PPYApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tamhoang on 31/05/2016.
 */
@Module
public class AppModule {

    private final PPYApplication mApplication;

    public AppModule(PPYApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public PPYApplication provideApplication() {
        return mApplication;
    }

}
