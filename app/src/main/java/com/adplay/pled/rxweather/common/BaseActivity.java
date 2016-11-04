package com.adplay.pled.rxweather.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by PLED on 2016/10/28.
 */
public class BaseActivity extends AppCompatActivity {
    Toast toast;
    protected Context mContext;
    public ProgressDialog progressDialog;
    private CompositeSubscription mCompositeSubscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }
    public void showToast(String msg){
        if (toast == null){
            toast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();

    }
    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
