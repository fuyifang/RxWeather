package com.adplay.pled.rxweather.presenter;

import com.adplay.pled.rxweather.common.BasePresenter;
import com.adplay.pled.rxweather.model.weatherModel;
import com.adplay.pled.rxweather.retrofit.ApiCallBack;
import com.adplay.pled.rxweather.retrofit.ApiStores;
import com.adplay.pled.rxweather.view.MainView;

/**
 * Created by PLED on 2016/10/28.
 */
public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter (MainView view){
        attachView(view);
    }
    public void getWeatherByIp(String ip){
        mvpView.showLoading();
        addSubscription(apiStores.getWeatherByIp(ApiStores.KEY, ip), new ApiCallBack<weatherModel>() {
            @Override
            public void onSuccess(weatherModel model) {
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
