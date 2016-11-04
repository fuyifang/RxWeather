package com.adplay.pled.rxweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adplay.pled.rxweather.R;
import com.adplay.pled.rxweather.model.WeatherModel;
import com.adplay.pled.rxweather.widget.IndexHorizontalScrollView;
import com.adplay.pled.rxweather.widget.Today24HourView;

/**
 * Recycler adapter
 *
 * @author Qiushui
 */
public class WeatherInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private static final int ITEM_COUNT = 10;
    int first = 0;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_DETAIL = 2;

    private WeatherModel mWeatherModel;

    public WeatherInfoAdapter(Context context,WeatherModel weatherModel) {
        this.mWeatherModel = weatherModel;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.weather_header, parent, false));
        }else if (viewType==TYPE_DETAIL){
            return new DetailHolder(LayoutInflater.from(context).inflate(R.layout.weather_detail,parent,false));
        }
        return new InfoHolder(LayoutInflater.from(context).inflate(R.layout.weather_info, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.txt_temperature.setText(mWeatherModel.getResult().get(0).getTemperature());
                break;
            case TYPE_DETAIL:
                Log.e("Tag","type_detail"+first);
                //避免多次调用导致多次重绘
                if (first == 0){
                    DetailHolder detailHolder = (DetailHolder) holder;
                    //TODO 解决温度变化大的绘制问题
                    detailHolder.hourView.initHourItems(mWeatherModel.getResult().get(0).getFuture());
                    detailHolder.horizontalScrollView.setToday24HourView(detailHolder.hourView);
                }
                first++;
                break;

        }

    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 5){
            return TYPE_DETAIL;
        }
        else {
            return TYPE_ITEM;
        }

    }

     class InfoHolder extends RecyclerView.ViewHolder {
        public InfoHolder(View itemView) {
            super(itemView);
        }
    }

     class HeaderHolder extends RecyclerView.ViewHolder {
        TextView txt_temperature;
        public HeaderHolder(View itemView) {
            super(itemView);
            txt_temperature = (TextView) itemView.findViewById(R.id.txt_temprature);
        }

    }
    class DetailHolder extends RecyclerView.ViewHolder{
        IndexHorizontalScrollView horizontalScrollView;
        Today24HourView hourView;

        public DetailHolder(View itemView) {
            super(itemView);
            horizontalScrollView = (IndexHorizontalScrollView) itemView.findViewById(R.id.index_scroll);
            hourView = (Today24HourView) itemView.findViewById(R.id.hourView);
        }
    }
}
