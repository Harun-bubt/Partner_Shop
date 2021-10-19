package com.example.partnershop;

import static com.example.partnershop.StoreSettings.getPathUrl;

import com.example.partnershop.Model.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PartnerShopServer {

    //------------------ Base URL ------------------
    String BASE_URL = StoreSettings.getBaseUrl();
    public   String PATH = StoreSettings.getPathUrl();
    //----------------------------------------------

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.NONE);


    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .callTimeout(6, TimeUnit.SECONDS)
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();


    //--------------- user login ---------------
    @FormUrlEncoded
    @POST()
    Call<Service> login(
            @Url String url,
            @Query("action") String action,
            @Field("partnershopid") int partnershopid,
            @Field("accesscode") int accesscode

    );
}
