package com.glite.popeyes.data.remote.api;

import com.glite.popeyes.data.remote.reponse.authen.AuthenResponse;
import com.glite.popeyes.data.remote.reponse.listordertime.ListOrderTimeResponse;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryResponse;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.MenuByCategoryResponse;
import com.glite.popeyes.data.remote.reponse.postalcode.PostalCodeResponse;
import com.glite.popeyes.data.remote.request.listordertime.ListOrderTimeRequest;
import com.glite.popeyes.data.remote.request.menu.ClientIDRequest;
import com.glite.popeyes.data.remote.request.menu.CategoryIDRequest;
import com.glite.popeyes.data.remote.request.postalcode.PostalCodeRequest;
import com.glite.popeyes.data.remote.request.auth.LoginRequest;
import com.glite.popeyes.data.remote.request.auth.RegisterRequest;
import com.glite.popeyes.data.remote.request.auth.SocialAuthRequest;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public interface PopeyesApi {

    // Login
    @POST("api/member/login")
    Observable<AuthenResponse> login(@Body LoginRequest loginRequest);

    // Register
    @POST("api/member/addmember")
    Observable<AuthenResponse> register(@Body RegisterRequest registerRequest);

    // Check Postal Code
    @POST("api/popeyes/getAddress")
    Observable<PostalCodeResponse> checkPostalCode(@Body PostalCodeRequest postalCodeRequest);

    // Login Social Network, include facebook and google
    @POST("api/member/sociallogin")
    Observable<AuthenResponse> socialLogin(@Body SocialAuthRequest authRequest);

    // Get MenuCategory by ClientId
    @POST("api/menucategory/getCategoryList")
    Observable<CategoryResponse> getCategories(@Body ClientIDRequest clientIdRequest);

    // Get Menu by CategoryID
    @POST("api/menu/getByCategory")
    Observable<MenuByCategoryResponse> getMenuByCategory(@Body CategoryIDRequest menuRequest);

        // Get List order time by postal code
    @POST("api/outlettime/listtimeslot")
    Observable<ListOrderTimeResponse> getListOrderTime(@Body ListOrderTimeRequest listOrderTimeRequest);
}
