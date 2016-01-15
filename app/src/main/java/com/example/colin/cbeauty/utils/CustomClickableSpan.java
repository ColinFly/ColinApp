package com.example.colin.cbeauty.utils;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @Description: 自定义ClickableSpan，部分文字可点击
 * @author: <a href="http://xiaoyaoyou1212.360doc.com">DAWI</a>
 * @date: 2015-08-31 13:51
 */
public class CustomClickableSpan extends ClickableSpan {

    private int mTextColor = Color.WHITE;//字体颜色
    private boolean isUnderline = false;//是否有下划线
    private OnClickPartText onClickPartText;

    public interface OnClickPartText {
        void onClickPart();
    }

    public void setOnClickPartText(OnClickPartText onClickPartText) {
        this.onClickPartText = onClickPartText;
    }

    public void setIsUnderline(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mTextColor);
        ds.setUnderlineText(isUnderline);
    }

    @Override
    public void onClick(View widget) {
        onClickPartText.onClickPart();
    }

}
