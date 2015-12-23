package com.example.colin.retrofit_test;

import android.os.Bundle;
import android.util.Log;

import com.example.colin.R;
import com.example.colin.base.BaseActivity;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by colin on 15-12-18.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiManager.getUserData("colin")
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Log.e("User", user.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("Throw", throwable.getMessage(), throwable);
                    }
                });
    }
}
