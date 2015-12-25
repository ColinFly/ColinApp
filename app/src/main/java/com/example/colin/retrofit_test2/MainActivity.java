package com.example.colin.retrofit_test2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.colin.R;


import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by colin on 15-12-18.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                break;
            case R.id.button2:
                LoginApi.getAuthModel("18820243175", "123456");
                break;
            case R.id.button3:
                UserApi.getUserModel();
//                BaseNetwork.getInstance().getUserInfo();
                break;
            case R.id.button4:
                break;
            case R.id.button5:

                break;
        }
    }


}
