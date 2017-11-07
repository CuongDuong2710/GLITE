package com.glite.popeyes.data;

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

import rx.Observable;

/**
 * @author Brian
 * @date: 31/08/2016
 */
public interface DataSource {

    Observable<AuthenResponse> login(final LoginRequest loginRequest);

    Observable<AuthenResponse> register(final RegisterRequest registerRequest);

    Observable<AuthenResponse> registerAutoLogin(final RegisterRequest registerRequest);

    Observable<PostalCodeResponse> checkPostalCode(final PostalCodeRequest postalCodeRequest);

    Observable<AuthenResponse> authSocial(final SocialAuthRequest authRequest);

    Observable<CategoryResponse> getCategories(final ClientIDRequest clientIDRequest);

    Observable<MenuByCategoryResponse> getMenuByCategory(final CategoryIDRequest categoryIDRequest);

    Observable<ListOrderTimeResponse> getListOrderTime(final ListOrderTimeRequest listOrderTimeRequest);
}
