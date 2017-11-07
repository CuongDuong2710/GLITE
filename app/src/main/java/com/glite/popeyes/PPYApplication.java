package com.glite.popeyes;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.glite.popeyes.injector.components.AppComponent;
import com.glite.popeyes.injector.components.DaggerAppComponent;
import com.glite.popeyes.injector.modules.AppModule;
import com.glite.popeyes.injector.modules.NetworkModule;

/**
 * Created by Administrator on 18/05/2016.
 */
public class PPYApplication extends Application {

    private AppComponent mAppComponent;

    private static PPYApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        initializeInjector();

        // Init facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_API_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static PPYApplication get() {
        if (mInstance == null) {
            mInstance = new PPYApplication();
        }
        return mInstance;
    }
}
