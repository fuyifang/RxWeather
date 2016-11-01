package com.adplay.pled.rxweather.view;

import com.adplay.pled.rxweather.common.BaseView;
import com.adplay.pled.rxweather.model.WeatherModel;

/**
 * Created by PLED on 2016/10/28.
 */
public interface MainView extends BaseView{
    void getDataSuccess(WeatherModel model);
    void getDataFail(String msg);
}
