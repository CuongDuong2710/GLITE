package com.glite.popeyes.injector.modules;

import com.glite.popeyes.BuildConfig;
import com.glite.popeyes.PPYApplication;
import com.glite.popeyes.data.remote.api.PopeyesApi;
import com.glite.popeyes.util.Config;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tamhoang on 01/06/2016.
 */
@Module
public class NetworkModule {

    private final String mUrl;

    public NetworkModule(String url) {
        this.mUrl = url;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(PPYApplication application) {
        return new Cache(application.getCacheDir(), Config.CACHE_SIZE);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(Config.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Config.READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(mUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    PopeyesApi makeService(Retrofit retrofit) {
        return retrofit.create(PopeyesApi.class);
    }
}
