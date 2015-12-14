package com.example.colin.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by colin on 15-12-14.
 */
public class BaseActivity  extends Activity implements View.OnClickListener{
    public Context mContext;
    public Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mResources = getResources();
        EventBus.getDefault().register(this);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(BaseEvent event) {
    }

    protected void startActivity(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(this, activity));
    }

    protected void startActivity(Class<? extends BaseActivity> activity, int flag) {
        Intent intent=new Intent(this,activity);
        intent.addFlags(flag);
        startActivity(intent);
    }
    protected void startActivity(Class<? extends BaseActivity> activity,Bundle bundle) {
        Intent intent=new Intent(this,activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
