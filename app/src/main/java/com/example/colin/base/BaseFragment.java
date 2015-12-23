package com.example.colin.base;

import android.app.Fragment;
import android.view.View;

/**
 * Created by colin on 15-12-15.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
