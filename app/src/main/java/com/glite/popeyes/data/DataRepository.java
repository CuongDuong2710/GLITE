package com.glite.popeyes.data;

import com.glite.popeyes.data.local.PreferenceHelper;
import com.glite.popeyes.data.remote.api.PopeyesApi;
import com.glite.popeyes.data.remote.reponse.authen.AuthenResponse;
import com.glite.popeyes.data.remote.reponse.listordertime.ListOrderTimeResponse;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryResponse;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.MenuByCategoryResponse;
import com.glite.popeyes.data.remote.reponse.postalcode.PostalCodeResponse;
import com.glite.popeyes.data.remote.request.auth.LoginRequest;
import com.glite.popeyes.data.remote.request.listordertime.ListOrderTimeRequest;
import com.glite.popeyes.data.remote.request.menu.CategoryIDRequest;
import com.glite.popeyes.data.remote.request.menu.ClientIDRequest;
import com.glite.popeyes.data.remote.request.postalcode.PostalCodeRequest;
import com.glite.popeyes.data.remote.request.auth.RegisterRequest;
import com.glite.popeyes.data.remote.request.auth.SocialAuthRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Brian
 * @date: 31/08/2016
 */
@Singleton
public class DataRepository implements DataSource {

    private final PreferenceHelper mPreferenceHelper;

    private final PopeyesApi mPopeyesApi;

    @Inject
    DataRepository(PopeyesApi popeyesApi, PreferenceHelper preferenceHelper) {
        this.mPopeyesApi = popeyesApi;
        this.mPreferenceHelper = preferenceHelper;
    }

    @Override
    public Observable<AuthenResponse> login(LoginRequest loginRequest) {
        return mPopeyesApi.login(loginRequest);
    }

    @Override
    public Observable<AuthenResponse> authSocial(SocialAuthRequest authRequest) {
        return mPopeyesApi.socialLogin(authRequest);
    }

    @Override
    public Observable<AuthenResponse> register(RegisterRequest registerRequest) {
        return mPopeyesApi.register(registerRequest);
    }

    @Override
    public Observable<AuthenResponse> registerAutoLogin(RegisterRequest registerRequest) {
        return mPopeyesApi.register(registerRequest)
                .concatMap(authenResponse -> {
                    if (authenResponse.getStatus()) {
                        final String email = authenResponse.getData().getMember().getEmail().trim();
                        final String paswordRaw = registerRequest.password.trim();
                        return mPopeyesApi.login(LoginRequest.create(email, paswordRaw, true));
                    }
                    return null;
                }).map(authenResponse -> {
                    if (authenResponse.getStatus()) {
                        // TODO: store info to sharePreference to use later
                    }
                    return authenResponse;
                });
    }

    @Override
    public Observable<PostalCodeResponse> checkPostalCode(PostalCodeRequest postalCodeRequest) {
        return mPopeyesApi.checkPostalCode(postalCodeRequest);
    }

    @Override
    public Observable<CategoryResponse> getCategories(ClientIDRequest clientIDRequest) {
        return mPopeyesApi.getCategories(clientIDRequest);
    }

    @Override
    public Observable<MenuByCategoryResponse> getMenuByCategory(CategoryIDRequest categoryIDRequest) {
        return mPopeyesApi.getMenuByCategory(categoryIDRequest);
    }

    @Override
    public Observable<ListOrderTimeResponse> getListOrderTime(ListOrderTimeRequest listOrderTimeRequest) {
        return mPopeyesApi.getListOrderTime(listOrderTimeRequest);
    }


}
