package com.adplay.pled.rxweather.common;

import android.util.Log;

import com.adplay.pled.rxweather.retrofit.ApiStores;
import com.adplay.pled.rxweather.retrofit.AppClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by PLED on 2016/10/28.
 */
public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
        apiStores = AppClient.retrofit().create(ApiStores.class);
    }
    public void datachView(){
        this.mvpView = null;
        onUnsubscribe();
    }
    public void onUnsubscribe() {
        Log.e("Tag","onUnsubscribe");
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
    public void addSubscription(Observable observable, Subscriber subscriber){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
