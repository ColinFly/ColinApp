package com.example.colin.cbeauty;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.colin.cbeauty.widget.TitleBar;

/**
 * Created by colin on 15-12-29.
 */
public class BaseActivity extends Activity {
    private TitleBar mTitleBar = null;
    public LayoutInflater mInflater;
    protected LinearLayout.LayoutParams mTitleLayoutParams;
    private RelativeLayout cb_base = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(this);
        mTitleBar = new TitleBar(this);

        mTitleLayoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        cb_base = new RelativeLayout(this);
        cb_base.setBackgroundColor(Color.rgb(255, 255, 255));
        cb_base.setFitsSystemWindows(true);

        cb_base.addView(mTitleBar, mTitleLayoutParams);

        RelativeLayout.LayoutParams layoutParamsContent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsContent.addRule(RelativeLayout.BELOW, mTitleBar.getId());

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(cb_base, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        super.setContentView(layoutResID);
    }
}
