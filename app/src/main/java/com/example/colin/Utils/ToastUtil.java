package com.example.colin.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.colin.R;

/**
 * Created by colin on 15-12-24.
 */
public class ToastUtil {
    private static Toast mToast;
    public static void showToast(Context context,String str) {
        if (mToast == null) {
            mToast=new Toast(context);
        }
        mToast.setText(str);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
