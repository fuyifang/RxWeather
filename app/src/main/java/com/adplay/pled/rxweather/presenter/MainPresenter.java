package com.adplay.pled.rxweather.presenter;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.adplay.pled.rxweather.common.BasePresenter;
import com.adplay.pled.rxweather.model.WeatherModel;
import com.adplay.pled.rxweather.retrofit.ApiCallBack;
import com.adplay.pled.rxweather.retrofit.ApiStores;
import com.adplay.pled.rxweather.view.MainView;

/**
 * Created by PLED on 2016/10/28.
 */
public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter (MainView view,Context context){
        attachView(view,context);
    }

    public void getWeatherByIp(String ip){
        mvpView.showLoading();
        addSubscription(apiStores.getWeatherByIp(ApiStores.KEY, ip), new ApiCallBack<WeatherModel>() {
            @Override
            public void onSuccess(WeatherModel model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);


            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();

            }
        });
    }
    public void getWeatherBycityName(String provincename,String cityName){
        mvpView.showLoading();
        addSubscription(apiStores.getWeatherByAddress(ApiStores.KEY, provincename,cityName), new ApiCallBack<WeatherModel>() {

            @Override
            public void onSuccess(WeatherModel model) {
                mvpView.getDataSuccess(model);

            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);

            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();

            }
        });

    }
}
