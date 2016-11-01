package com.adplay.pled.rxweather.retrofit;

import com.adplay.pled.rxweather.model.WeatherModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PLED on 2016/10/28.
 */
public interface ApiStores {
    String API_SERVER_URL = "http://apicloud.mob.com/v1/weather/";
    String KEY = "520520test";


    @GET("query")
    Observable<WeatherModel> getWeatherByAddress(@Query("province") String province, @Query("city") String city);
    //uri=http://apicloud.mob.com/v1/weather/ip?&key=520520test&ip=219.134.48.105
    @GET("ip")
    Observable<WeatherModel> getWeatherByIp(@Query("key") String key, @Query("ip") String ip);



}
