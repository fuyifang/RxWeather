package com.adplay.pled.rxweather.adapter;

import android.content.Context;
import android.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adplay.pled.rxweather.R;
import com.adplay.pled.rxweather.model.WeatherModel;

/**
 * Recycler adapter
 *
 * @author Qiushui
 */
public class WeatherInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private static final int ITEM_COUNT = 10;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private WeatherModel mWeatherModel;

    public WeatherInfoAdapter(Context context,WeatherModel weatherModel) {
        this.mWeatherModel = weatherModel;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_header, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.txt_temperature.setText(mWeatherModel.getResult().get(0).getTemperature());
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
        } else {
            return TYPE_ITEM;
        }

    }

     class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
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
}
