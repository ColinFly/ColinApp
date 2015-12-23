package com.example.colin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colin.base.BaseFragment;
import com.example.colin.fragments.SchedualFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by colin on 15-12-15.
 */
public class MainFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;

    }

    @OnClick({R.id.btn_schedules})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_schedules:
                clickedOn(new SchedualFragment());
                break;
        }
    }

    public void clickedOn(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }
}


