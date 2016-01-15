/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.colin.cbeauty.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * <p>描述：View工具类</p>
 * @author ~若相惜
 * @date 2015-9-1 下午5:47:50
 * @version v1.0
 *
 */
public class AbViewUtil {
	/**  UI设计的基准宽度. */
	public static int UI_WIDTH = 720;
	
	/**  UI设计的基准高度. */
	public static int UI_HEIGHT = 1280;
	/**  UI设计的密度. */
	public static int UI_DENSITY = 2;
    
    /**
     * 无效值
     */
    public static final int INVALID = Integer.MIN_VALUE;
    
	/**
	 * 描述：重置AbsListView的高度. item 的最外层布局要用
	 * RelativeLayout,如果计算的不准，就为RelativeLayout指定一个高度
	 * 
	 * @param absListView
	 *            the abs list view
	 * @param lineNumber
	 *            每行几个 ListView一行一个item
	 * @param verticalSpace
	 *            the vertical space
	 */
	public static void setAbsListViewHeight(AbsListView absListView,
			int lineNumber, int verticalSpace) {

		int totalHeight = getAbsListViewHeight(absListView, lineNumber,
				verticalSpace);
		LayoutParams params = absListView.getLayoutParams();
		params.height = totalHeight;
		((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
		absListView.setLayoutParams(params);
	}

	/**
	 * 描述：获取AbsListView的高度.
	 *
	 * @param absListView            the abs list view
	 * @param lineNumber            每行几个 ListView一行一个item
	 * @param verticalSpace            the vertical space
	 * @return the abs list view height
	 */
	public static int getAbsListViewHeight(AbsListView absListView,
			int lineNumber, int verticalSpace) {
		int totalHeight = 0;
		int w = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		int h = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		absListView.measure(w, h);
		ListAdapter mListAdapter = absListView.getAdapter();
		if (mListAdapter == null) {
			return totalHeight;
		}

		int count = mListAdapter.getCount();
		if (absListView instanceof ListView) {
			for (int i = 0; i < count; i++) {
				View listItem = mListAdapter.getView(i, null, absListView);
				listItem.measure(w, h);
				totalHeight += listItem.getMeasuredHeight();
			}
			if (count == 0) {
				totalHeight = verticalSpace;
			} else {
				totalHeight = totalHeight
						+ (((ListView) absListView).getDividerHeight() * (count - 1));
			}

		} else if (absListView instanceof GridView) {
			int remain = count % lineNumber;
			if (remain > 0) {
				remain = 1;
			}
			if (mListAdapter.getCount() == 0) {
				totalHeight = verticalSpace;
			} else {
				View listItem = mListAdapter.getView(0, null, absListView);
				listItem.measure(w, h);
				int line = count / lineNumber + remain;
				totalHeight = line * listItem.getMeasuredHeight() + (line - 1)
						* verticalSpace;
			}

		}
		return totalHeight;

	}

	/**
	 * 测量这个view
	 * 最后通过getMeasuredWidth()获取宽度和高度.
	 * @param view 要测量的view
	 * @return 测量过的view
	 */
	public static void measureView(View view) {
		LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		view.measure(childWidthSpec, childHeightSpec);
	}
	
	/**
	 * 获得这个View的宽度
	 * 测量这个view，最后通过getMeasuredWidth()获取宽度.
	 * @param view 要测量的view
	 * @return 测量过的view的宽度
	 */
	public static int getViewWidth(View view) {
		measureView(view);
		return view.getMeasuredWidth();
	}
	
	/**
	 * 获得这个View的高度
	 * 测量这个view，最后通过getMeasuredHeight()获取高度.
	 * @param view 要测量的view
	 * @return 测量过的view的高度
	 */
	public static int getViewHeight(View view) {
		measureView(view);
		return view.getMeasuredHeight();
	}
	
	/**
	 * 从父亲布局中移除自己
	 * @param v
	 */
	public static void removeSelfFromParent(View v) {
		ViewParent parent = v.getParent();
		if(parent != null){
			if(parent instanceof ViewGroup){
				((ViewGroup)parent).removeView(v);
			}
		}
	}

	
	/**
     * 描述：dip转换为px.
     *
     * @param context the context
     * @param dipValue the dip value
     * @return px值
     */
    public static float dip2px(Context context, float dipValue) {
        DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
        return applyDimension(TypedValue.COMPLEX_UNIT_DIP,dipValue,mDisplayMetrics);
    }

    /**
     * 描述：px转换为dip.
     *
     * @param context the context
     * @param pxValue the px value
     * @return dip值
     */
    public static float px2dip(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
        return pxValue / mDisplayMetrics.density;
    }
    
    /**
     * 描述：sp转换为px.
     *
     * @param context the context
     * @param spValue the sp value
     * @return sp值
     */
    public static float sp2px(Context context, float spValue) {
        DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
        return applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,mDisplayMetrics);
    }
    
    /**
     * 描述：px转换为sp.
     *
     * @param context the context
     * @param pxValue the sp value
     * @return sp值
     */
    public static float px2sp(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
        return pxValue / mDisplayMetrics.scaledDensity;
    }

	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param context the context
	 * @param value the px value
	 * @return the int
	 */
	public static int scaleValue(Context context, float value) {
		DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
		//为了兼容尺寸小密度大的情况
		if(mDisplayMetrics.scaledDensity > UI_DENSITY){
			//密度
			if(mDisplayMetrics.widthPixels > UI_WIDTH){
				value = value*(1.3f - 1.0f/mDisplayMetrics.scaledDensity);
			}else if(mDisplayMetrics.widthPixels <UI_WIDTH){
				value = value*(1.0f - 1.0f/mDisplayMetrics.scaledDensity);
			}
		}
		return scale(mDisplayMetrics.widthPixels,
				mDisplayMetrics.heightPixels, value);
	}
	
	/**
	 * 描述：根据屏幕大小缩放文本.
	 *
	 * @param context the context
	 * @param value the px value
	 * @return the int
	 */
	public static int scaleTextValue(Context context, float value) {
		DisplayMetrics mDisplayMetrics = AbAppUtil.getDisplayMetrics(context);
		//为了兼容尺寸小密度大的情况
		if(mDisplayMetrics.scaledDensity > 2){
			//缩小到密度分之一
			//value = value*(1.1f - 1.0f/mDisplayMetrics.scaledDensity);
		}
		return scale(mDisplayMetrics.widthPixels,
				mDisplayMetrics.heightPixels, value);
	}
	
	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param displayWidth the display width
	 * @param displayHeight the display height
	 * @param pxValue the px value
	 * @return the int
	 */
	public static int scale(int displayWidth, int displayHeight, float pxValue) {
		if(pxValue == 0 ){
			return 0;
		}
		float scale = 1;
		try {
			float scaleWidth = (float) displayWidth / UI_WIDTH;
			float scaleHeight = (float) displayHeight / UI_HEIGHT;
			scale = Math.min(scaleWidth, scaleHeight);
		} catch (Exception e) {
		}
		return Math.round(pxValue * scale + 0.5f);
	}

	
	/**
	 * TypedValue官方源码中的算法，任意单位转换为PX单位
	 * @param unit  TypedValue.COMPLEX_UNIT_DIP
	 * @param value 对应单位的值
	 * @param metrics 密度
	 * @return px值
	 */
    public static float applyDimension(int unit, float value,
                                       DisplayMetrics metrics){
        switch (unit) {
        case TypedValue.COMPLEX_UNIT_PX:
            return value;
        case TypedValue.COMPLEX_UNIT_DIP:
            return value * metrics.density;
        case TypedValue.COMPLEX_UNIT_SP:
            return value * metrics.scaledDensity;
        case TypedValue.COMPLEX_UNIT_PT:
            return value * metrics.xdpi * (1.0f/72);
        case TypedValue.COMPLEX_UNIT_IN:
            return value * metrics.xdpi;
        case TypedValue.COMPLEX_UNIT_MM:
            return value * metrics.xdpi * (1.0f/25.4f);
        }
        return 0;
    }
    
	
	/**
	 * 
	 * 描述：View树递归调用做适配.
	 * AbAppConfig.uiWidth = 1080;
	 * AbAppConfig.uiHeight = 700;
	 * scaleContentView((RelativeLayout)findViewById(R.id.rootLayout));
	 * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
	 * @param contentView
	 */
    public static void scaleContentView(ViewGroup contentView){
    	AbViewUtil.scaleView(contentView);
		if(contentView.getChildCount()>0){
			for(int i=0;i<contentView.getChildCount();i++){
				View view = contentView.getChildAt(i);
				if(view instanceof ViewGroup){
					if(isNeedScale(view)){
						scaleContentView((ViewGroup)(view));
			    	}
				}else{
					scaleView(contentView.getChildAt(i));
				}
			}
		}
    }
    
    /**
	 * 
	 * 描述：View树递归调用做适配.
	 * AbAppConfig.uiWidth = 1080;
	 * AbAppConfig.uiHeight = 700;
	 * scaleContentView(context,R.id.rootLayout);
	 * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
	 * @param parent
	 * @param id
	 */
    public static void scaleContentView(View parent,int id){
    	ViewGroup contentView = null;
    	View view = parent.findViewById(id);
    	if(view instanceof ViewGroup){
    		contentView = (ViewGroup)view;
    		scaleContentView(contentView);
    	}
    }
    
    /**
	 * 
	 * 描述：View树递归调用做适配.
	 * AbAppConfig.uiWidth = 1080;
	 * AbAppConfig.uiHeight = 700;
	 * scaleContentView(context,R.id.rootLayout);
	 * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
	 * @param context
	 * @param id
	 */
    public static void scaleContentView(Context context,int id){
    	ViewGroup contentView = null;
    	View view = ((Activity)context).findViewById(id);
    	if(view instanceof ViewGroup){
    		contentView = (ViewGroup)view;
    		scaleContentView(contentView);
    	}
    }
    
    /**
     * 按比例缩放View，以布局中的尺寸为基准
     * @param view
     */
    @SuppressLint("NewApi")
	public static void scaleView(View view){
    	if(!isNeedScale(view)){
    		return;
    	}
        if (view instanceof TextView){
            TextView textView = (TextView) view;
            setTextSize(textView,textView.getTextSize());
        }

        LayoutParams params = (LayoutParams) view.getLayoutParams();
        if (null != params){
            int width = INVALID;
            int height = INVALID;
            if (params.width != LayoutParams.WRAP_CONTENT
                && params.width != LayoutParams.MATCH_PARENT){
                width = params.width;
            }

            if (params.height != LayoutParams.WRAP_CONTENT
                && params.height != LayoutParams.MATCH_PARENT){
                height = params.height;
            }
            
            //size
            setViewSize(view,width,height);

            // Padding
            setPadding(view,view.getPaddingLeft(),view.getPaddingTop(),view.getPaddingRight(),view.getPaddingBottom());
        }
        
        // Margin
        if(view.getLayoutParams() instanceof MarginLayoutParams){
            MarginLayoutParams mMarginLayoutParams = (MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null){
                setMargin(view,mMarginLayoutParams.leftMargin,mMarginLayoutParams.topMargin,mMarginLayoutParams.rightMargin,mMarginLayoutParams.bottomMargin);
            }
        }
        
        if(VERSION.SDK_INT>=16){
        	//最大最小宽高
            int minWidth = scaleValue(view.getContext(),view.getMinimumWidth());
            int minHeight = scaleValue(view.getContext(),view.getMinimumHeight());
            view.setMinimumWidth(minWidth);
            view.setMinimumHeight(minHeight);
        }
    }
    
    /**
     * 
     * 描述：是否需要Scale.
     * @param view
     * @return
     */
    public static boolean isNeedScale(View view){
    	/*if (view instanceof AbListViewHeader){
    		return false;
        }
    	
    	if (view instanceof AbListViewFooter){
    		return false;
        }*/
    	return true;
    }
    
    /**
     * 缩放文字大小
     * @param textView button
     * @param size sp值
     * @return
     */
    public static void setSPTextSize(TextView textView,float size) {
    	float scaledSize = scaleTextValue(textView.getContext(),size);
        textView.setTextSize(scaledSize);
    }
    
    /**
     * 缩放文字大小,这样设置的好处是文字的大小不和密度有关，
     * 能够使文字大小在不同的屏幕上显示比例正确
     * @param textView button
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(TextView textView,float sizePixels) {
    	float scaledSize = scaleTextValue(textView.getContext(),sizePixels);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,scaledSize);
    }
    
    /**
     * 缩放文字大小
     * @param context
     * @param textPaint
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(Context context,TextPaint textPaint,float sizePixels) {
    	float scaledSize = scaleTextValue(context,sizePixels);
    	textPaint.setTextSize(scaledSize);
    }
    
    /**
     * 缩放文字大小
     * @param context
     * @param paint
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(Context context,Paint paint,float sizePixels) {
    	float scaledSize = scaleTextValue(context,sizePixels);
    	paint.setTextSize(scaledSize);
    }
    
   /**
    * 设置View的PX尺寸
    * @param view  如果是代码new出来的View，需要设置一个适合的LayoutParams
    * @param widthPixels
    * @param heightPixels
    */
    public static void setViewSize(View view,int widthPixels, int heightPixels){
        int scaledWidth = scaleValue(view.getContext(), widthPixels);
        int scaledHeight = scaleValue(view.getContext(), heightPixels);
        LayoutParams params = view.getLayoutParams();
        if(params == null){
           // AbLogUtil.e(AbViewUtil.class, "setViewSize出错,如果是代码new出来的View，需要设置一个适合的LayoutParams");
            return;
        }
        if (widthPixels != INVALID){
            params.width = scaledWidth;
        }
        if (heightPixels != INVALID){
            params.height = scaledHeight;
        }
        view.setLayoutParams(params);
    }

	/**
	 * 设置PX padding.
	 *
	 * @param view the view
	 * @param left the left padding in pixels
     * @param top the top padding in pixels
     * @param right the right padding in pixels
     * @param bottom the bottom padding in pixels
	 */
	public static void setPadding(View view, int left,
			int top, int right, int bottom) {
		int scaledLeft = scaleValue(view.getContext(), left);
		int scaledTop = scaleValue(view.getContext(), top);
		int scaledRight = scaleValue(view.getContext(), right);
		int scaledBottom = scaleValue(view.getContext(), bottom);
		view.setPadding(scaledLeft, scaledTop, scaledRight, scaledBottom);
	}

	/**
	 * 设置 PX margin.
	 * 
	 * @param view the view
	 * @param left the left margin in pixels
	 * @param top the top margin in pixels
	 * @param right the right margin in pixels
	 * @param bottom the bottom margin in pixels
	 */
	public static void setMargin(View view, int left, int top,
			int right, int bottom) {
		int scaledLeft = scaleValue(view.getContext(), left);
		int scaledTop = scaleValue(view.getContext(), top);
		int scaledRight = scaleValue(view.getContext(), right);
		int scaledBottom = scaleValue(view.getContext(), bottom);
		
		if(view.getLayoutParams() instanceof MarginLayoutParams){
            MarginLayoutParams mMarginLayoutParams = (MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null){
                if (left != INVALID) {
                    mMarginLayoutParams.leftMargin = scaledLeft;
                }
                if (right != INVALID) {
                    mMarginLayoutParams.rightMargin = scaledRight;
                }
                if (top != INVALID) {
                    mMarginLayoutParams.topMargin = scaledTop;
                }
                if (bottom != INVALID) {
                    mMarginLayoutParams.bottomMargin = scaledBottom;
                }
                view.setLayoutParams(mMarginLayoutParams);
            }
        }
		
	}
	
	/**
	 * 修改普通View的高<br>
	 * Adapter---getView方法中慎用
	 */
	public static void changeH(View v, int H) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.height = H;
		v.setLayoutParams(params);
	}

	/**
	 * 修改普通View的宽<br>
	 * Adapter---getView方法中慎用
	 */
	public static void changeW(View v, int W) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.width = W;
		v.setLayoutParams(params);
	}

	/**
	 * 修改控件的宽高<br>
	 * Adapter---getView方法中慎用
	 * 
	 * @param v
	 *            控件
	 * @param W
	 *            宽度
	 * @param H
	 *            高度
	 */
	public static void changeWH(View v, int W, int H) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.width = W;
		params.height = H;
		v.setLayoutParams(params);
	}
	
	/**
	 * 
	 * <p>描述:设置listview的高度</p> 
	 * @param listView
	 * @param itemHeight item的高度
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView,int itemHeight) {
	    //获取listview的适配器
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        return;
	    }

	    int totalHeight = 0;

	    for (int i = 0; i < listAdapter.getCount(); i++) {
	    	 View listItem = listAdapter.getView(i, null, listView);
	         listItem.measure(0, 0);
	         totalHeight += listItem.getMeasuredHeight();
	    }

	    LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight;
	    listView.setLayoutParams(params);
	}
	
	/**
	 * 将layout转成View
	 * @param context
	 * @param layoutId
	 * @return
	 */
	public static View layoutToView(Context context,int layoutId){
		LayoutInflater inflater= LayoutInflater.from(context);
		View v=inflater.inflate(layoutId, null);
		return v;
	}
	
	/**
	 * 
	 * <p>描述:水平移动view</p> 
	 * 
	 * 移动到目标位置 slideview(0, distance);<br>
     * 从目标位置移回原位slideview(0, -distance); <br>
     * 在动画执行完毕后（onAnimationEnd）设置view的位置，同时要clearAnimation()<br>
     * 注：clearAnimation() 必须在 layout(l,t,r,b) 前执行，否则会出错~<br>
	 * @param view
	 * @param p1
	 * @param p2    设定文件
	 */
	public static void slideview(final View view,final float p1, final float p2) {
		TranslateAnimation animation = new TranslateAnimation(p1, p2, 0, 0);
		animation.setInterpolator(new OvershootInterpolator());
		animation.setDuration(500);
		animation.setStartOffset(200);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				int left = view.getLeft() + (int) (p2 - p1);
				int top = view.getTop();
				int width = view.getWidth();
				int height = view.getHeight();
				view.clearAnimation();
				view.layout(left, top, left + width, top + height);
			}
		});
		view.startAnimation(animation);
	}

	/**
	 * 设置部分文字高亮显示并可点击
	 * @param listener 点击事件
	 * @param textView 控件
	 * @param isUnderline 是否有下划线
	 * @param color 高亮文字颜色
	 * @param start 高亮文字开始位置
	 * @param end 高亮文字结束位置
	 */
	public static void setPartTextClickable(final CustomClickableSpan.OnClickPartText listener, TextView textView, boolean isUnderline, int color, int start, int end){
		if(textView == null || !(textView.getText().length() > 0) || end < start || end > textView.getText().length()){
			return;
		}
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
		CustomClickableSpan customClickableSpan = new CustomClickableSpan();
		customClickableSpan.setIsUnderline(isUnderline);
		customClickableSpan.setmTextColor(color);
		customClickableSpan.setOnClickPartText(new CustomClickableSpan.OnClickPartText() {
			@Override
			public void onClickPart() {
				if (listener != null) {
					listener.onClickPart();
				}
			}
		});
		spannableStringBuilder.setSpan(customClickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(spannableStringBuilder);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	/**
	 * 
	 * <p>描述:动画显示 从上到下移动显示 ，可以设置展示几秒后消失</p> 
	 * 此模式为默认模式<br>
	 * 1.默认展示2秒后消失
	 * 2.移动的距离是控件的高度
	 * @param view  需要动画的view
	 */
	public static void playAnimation(final View view){
		int h=getViewHeight(view);
		playVisibleAnimation(view, h, 2000);
	}
	
	/** 
	 * <p>描述:TODO(这里用一句话描述这个方法的作用)</p> 
	 * @param view  需要动画的view
	 * @param duration    设定几秒消失
	 */
	public static void playAnimation(final View view,final int duration){
		int h=getViewHeight(view);
		playVisibleAnimation(view, h, duration);
	}
	
	/**
	 * 
	 * <p>描述:动画显示 从上到下移动显示 ，可以设置展示几秒后消失</p> 
	 * @param view  需要动画的view
	 * @param offsetY  移动的距离  （控件的高度）
	 * @param duration    设置展示几秒后消失
	 */
	public static void playVisibleAnimation(final View view,final int offsetY,final int duration){
		view.setVisibility(View.VISIBLE);
		float originalY =  - offsetY;
		float finalY = 0;
		//透明度控制动画效果 alpha   
		AlphaAnimation animation_alpha=new AlphaAnimation(0.1f,1.0f);
		//第一个参数fromAlpha为 动画开始时候透明度   
		//第二个参数toAlpha为 动画结束时候透明度   
		animation_alpha.setRepeatCount(0);//设置循环   
		animation_alpha.setDuration(800);//设置时间持续时间为 500毫秒   
		
		//移动动画效果translate   
		TranslateAnimation animation_translate=new TranslateAnimation(0, 0, originalY,finalY);
		//第一个参数fromXDelta为动画起始时 X坐标上的移动位置       
		//第二个参数toXDelta为动画结束时 X坐标上的移动位置         
		//第三个参数fromYDelta为动画起始时Y坐标上的移动位置    
		//第三个参数toYDelta为动画结束时Y坐标上的移动位置    
		animation_translate.setRepeatCount(0);//设置动画执行多少次，如果是-1的话就是一直重复   
		animation_translate.setDuration(800);//设置时间持续时间为 500毫秒   
		
		AnimationSet animationSet=new AnimationSet(true);
		animationSet.addAnimation(animation_alpha);//透明度   
		animationSet.addAnimation(animation_translate);//移动   
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//两秒后再调
				view.postDelayed(new Runnable(){
					@Override
					public void run(){
						playGoneAnimation(view, offsetY);
					}
				}, duration);
			}
		});
		view.startAnimation(animationSet);//开始播放   
	}
	
	/**
	 * 
	 * <p>描述:设置view顶部消失动画</p> 
	 * @param view  隐藏的动画
	 * @param offsetY    垂直移动距离
	 */
	public static void playGoneAnimation(final View view,int offsetY){
		view.setVisibility(View.GONE);
		float originalY = 0;
	    float finalY =  - offsetY;
		//透明度控制动画效果 alpha   
		AlphaAnimation animation_alpha=new AlphaAnimation(1.0f,0.0f);
        //第一个参数fromAlpha为 动画开始时候透明度   
        //第二个参数toAlpha为 动画结束时候透明度   
        animation_alpha.setRepeatCount(0);//设置循环   
        animation_alpha.setDuration(800);//设置时间持续时间为 500毫秒   
          
        //移动动画效果translate   
        TranslateAnimation animation_translate=new TranslateAnimation(0, 0, originalY,finalY);
        //第一个参数fromXDelta为动画起始时 X坐标上的移动位置       
        //第二个参数toXDelta为动画结束时 X坐标上的移动位置         
        //第三个参数fromYDelta为动画起始时Y坐标上的移动位置    
        //第三个参数toYDelta为动画结束时Y坐标上的移动位置    
        animation_translate.setRepeatCount(0);//设置动画执行多少次，如果是-1的话就是一直重复   
        animation_translate.setDuration(500);//设置时间持续时间为 500毫秒   
          
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(animation_alpha);//透明度   
        animationSet.addAnimation(animation_translate);//移动   
        /*animationSet.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            	view.setVisibility(View.GONE);
            }
        });*/
        view.startAnimation(animationSet);//开始播放   
	}

	/**
	 * Drawable转换成Bitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}
}
