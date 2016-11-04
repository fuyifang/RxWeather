package com.adplay.pled.rxweather.model;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by user on 2016/10/19.
 */
public class HourItem {

    private  String time; //时间点
    private Rect windyBoxRect; //表示风力的box
    private String windy; //风力
    private int temperature; //温度
    private Point tempPoint; //温度的点坐标

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Rect getWindyBoxRect() {
        return windyBoxRect;
    }

    public void setWindyBoxRect(Rect windyBoxRect) {
        this.windyBoxRect = windyBoxRect;
    }

    public String getWindy() {
        return windy;
    }

    public void setWindy(String windy) {
        this.windy = windy;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Point getTempPoint() {
        return tempPoint;
    }

    public void setTempPoint(Point tempPoint) {
        this.tempPoint = tempPoint;
    }

    private int res = -1; //图片资源(有则绘制)
}
