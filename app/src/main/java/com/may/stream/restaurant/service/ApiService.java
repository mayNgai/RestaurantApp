package com.may.stream.restaurant.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblDataLogin;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.model.TblTables;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by may on 1/9/2018.
 */

public class ApiService {
    public static final String FORUM_SERVER_URL = "http://192.168.1.36:3100/";//10.255.248.63//127.0.0.1

    private ForumApi mForumApi;
    public ApiService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //RequestBody data = RequestBody.create(MediaType.parse("application/json"), stringJson);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORUM_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        mForumApi = retrofit.create(ForumApi.class);
    }

    public ForumApi getApi() {

        return mForumApi;
    }

    public interface ForumApi {

        /********* insert *********/
        @POST("new_company")
        public Observable<TblCompany> createCompany(@Body TblCompany company);

        @POST("register_member")
        public Observable<TblMember> createMember(@Body TblMember member);

        @POST("product")
        public Observable<TblProducts> createProduct(@Body TblProducts products);

        @POST("product_type")
        public Observable<TblProductTypes> createProductType(@Body TblProductTypes productTypes);

        @POST("discount")
        public Observable<TblDiscount> createDiscount(@Body TblDiscount tblDiscount);

        @POST("tables")
        public Observable<List<TblTables>> createTables(@Body List<TblTables> tablesList);

        /********* update *********/
        @POST("update_company")
        public Observable<TblCompany> updateCompany(@Body TblCompany company);

        @POST("update_product_type")
        public Observable<TblProductTypes> updateProductType(@Body TblProductTypes productTypes);

        @POST("update_product")
        public Observable<TblProducts> updateProduct(@Body TblProducts products);

        @POST("update_discount")
        public Observable<TblDiscount> updateDiscount(@Body TblDiscount discount);

        /********* delete *********/
        @POST("delete_product_type")
        public Observable<TblProductTypes> deleteProductType(@Body TblProductTypes productTypes);

        @POST("delete_product")
        public Observable<TblProducts> deleteProduct(@Body TblProducts products);

        @POST("delete_discount")
        public Observable<TblDiscount> deleteDiscount(@Body TblDiscount discount);

        @POST("delete_table")
        public Observable<TblTables> deleteTable(@Body TblTables tables);

        /********* select *********/

        @POST("get_login")
        public Observable<TblMember> getLogin(@Body TblMember member);

        @POST("get_data_login")
        public Observable<TblDataLogin> getDataLogin(@Body TblCompany company);

        @POST("get_store")
        public Observable<List<TblStore>> getStore();

        @POST("get_product_type")
        public Observable<List<TblProductTypes>> getProductTypes(@Body TblCompany company);

        @POST("get_product")
        public Observable<List<TblProducts>> getProducts(@Body TblCompany company);

        @POST("get_discount")
        public Observable<List<TblDiscount>> getDiscount(@Body TblCompany company);

        @POST("get_tables")
        public Observable<List<TblTables>> getTables(@Body TblCompany company);
    }
}
