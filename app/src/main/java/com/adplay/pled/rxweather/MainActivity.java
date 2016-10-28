package com.adplay.pled.rxweather;

import android.os.Bundle;
import android.widget.TextView;

import com.adplay.pled.rxweather.common.BaseActivity;
import com.adplay.pled.rxweather.model.weatherModel;
import com.adplay.pled.rxweather.presenter.MainPresenter;
import com.adplay.pled.rxweather.view.MainView;

public class MainActivity extends BaseActivity implements MainView {
    MainPresenter mPresenter;
    final String cityip = "219.134.48.105";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.weather_info);
        mPresenter = new MainPresenter(this);
        mPresenter.getWeatherByIp(cityip);
    }

    @Override
    public void getDataSuccess(weatherModel model) {
        textView.setText( model.getResult().get(0).getCity());

    }

    @Override
    public void getDataFail(String msg) {
        showToast("网络不给力"+msg);

    }

    @Override
    public void showLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.datachView();

    }

    @Override
    public void hideLoading() {

    }
}
