package com.adplay.pled.rxweather.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PLED on 2016/10/28.
 */
public class AppClient {
    public static Retrofit mRetrofit;
    public static Retrofit retrofit(){
        if (mRetrofit==null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }
        return mRetrofit;
    }
}
