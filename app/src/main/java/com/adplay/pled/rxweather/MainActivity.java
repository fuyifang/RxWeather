package com.adplay.pled.rxweather;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.adplay.pled.rxweather.adapter.WeatherInfoAdapter;
import com.adplay.pled.rxweather.common.BaseActivity;
import com.adplay.pled.rxweather.model.WeatherModel;
import com.adplay.pled.rxweather.presenter.MainPresenter;
import com.adplay.pled.rxweather.view.MainView;
import com.adplay.pled.rxweather.widget.BlurredView;
import com.adplay.pled.rxweather.widget.IndexHorizontalScrollView;
import com.adplay.pled.rxweather.widget.Today24HourView;

public class MainActivity extends BaseActivity implements MainView {
    MainPresenter mPresenter;
    final String cityip = "219.134.48.105";
    private RecyclerView mRecyclerView;
    private BlurredView mBlurredView;
    private TextView txt_address;
    private int mScrollerY;

    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        mPresenter = new MainPresenter(this);
        mPresenter.getWeatherByIp(cityip);
    }

    private void initView() {
        txt_address = (TextView) findViewById(R.id.txt_address);
        mRecyclerView = (RecyclerView) findViewById(R.id.weather_info);
        mBlurredView = (BlurredView) findViewById(R.id.blurview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    mBlurredView.setBlurredTop(100);
                    mAlpha = 100;
                } else {
                    mBlurredView.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                mBlurredView.setBlurredLevel(mAlpha);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });


    }

    @Override
    public void getDataSuccess(WeatherModel model) {
        txt_address.setText(model.getResult().get(0).getCity());
        mRecyclerView.setAdapter(new WeatherInfoAdapter(this,model));

    }

    @Override
    public void getDataFail(String msg) {
        showToast("网络不给力"+msg);
        Log.e("Tag",msg);

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
