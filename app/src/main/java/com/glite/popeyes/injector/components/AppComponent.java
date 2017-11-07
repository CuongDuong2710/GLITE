package com.glite.popeyes.injector.components;

import com.glite.popeyes.PPYApplication;
import com.glite.popeyes.data.DataRepository;
import com.glite.popeyes.data.local.AuthManager;
import com.glite.popeyes.data.local.OrdersManger;
import com.glite.popeyes.injector.modules.AppModule;
import com.glite.popeyes.injector.modules.NetworkModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tamhoang on 31/05/2016.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    PPYApplication application();

    Gson gson();

    AuthManager authManger();

    OrdersManger orderManager();

    DataRepository dataManager();

}
