package com.example.colin.retrofit_test2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.colin.R;
import com.example.colin.Utils.SysInfoUtil;
import com.example.colin.user.LoginApi;

/**
 * Created by colin on 15-12-18.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        SysInfoUtil.getUserAgent(context, "10010");
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApi.getInstance().getContributors();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BaseApi.getInstance().getContributors2();
//                BaseApi.getInstance().login();
                LoginApi.login("18820243175", "123456");
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApi.getInstance().getUserInfo();
            }
        });
    }

}
