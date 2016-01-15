package com.example.colin.cbeauty.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.colin.cbeauty.utils.AbViewUtil;
import com.example.colin.cbeauty.utils.StringUtil;


/**
 * ===万能标题栏===
 * <p>描述：标题栏实现.</p>
 * @author ~若相惜
 * @version v1.0
 *
 */
public class TitleBar extends LinearLayout {
	
	/** The m context. */
	private Activity mActivity;
	
	/** 标题布局. */
	protected LinearLayout titleTextLayout = null;
	
	/** 显示标题文字的View. */
	protected Button titleTextBtn = null;
	
	/** 显示标题文字的小View. */
	protected Button titleSmallTextBtn = null;
	
	/** 左侧的Logo图标View. */
	protected ImageView logoView = null;
	
	/** 左侧的Logo图标View. */
	protected ImageView logoView2 = null;
	
	/** 左侧的Logo图标右边的分割线View. */
	protected ImageView logoLineView = null;
	
	/** 标题文本的对齐参数. */
	private LayoutParams titleTextLayoutParams = null;
	
	/** 右边布局的的对齐参数. */
	private LayoutParams rightViewLayoutParams = null;
	
	/** 左边布局的的对齐参数. */
	private LayoutParams leftViewLayoutParams = null;
	
	/** 右边的View，可以自定义显示什么. */
	protected LinearLayout rightLayout = null;
	
	/** 左边的View，可以自定义显示什么. */
	protected LinearLayout leftLayout = null;
	
	/** 标题栏布局ID. */
	public int mAbTitleBarID = 1;
	
	/** 全局的LayoutInflater对象，已经完成初始化. */
	public LayoutInflater mInflater;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为FILL_PARENT, FILL_PARENT
	 */
	public LayoutParams layoutParamsFF = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为FILL_PARENT, WRAP_CONTENT
	 */
	public LayoutParams layoutParamsFW = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为WRAP_CONTENT, FILL_PARENT
	 */
	public LayoutParams layoutParamsWF = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为WRAP_CONTENT, WRAP_CONTENT
	 */
	public LayoutParams layoutParamsWW = null;
	
	/** 下拉选择. */
	private PopupWindow popupWindow;

	/**
	 * Instantiates a new ab title bar.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		ininTitleBar(context);
	}

	/**
	 * Instantiates a new ab title bar.
	 *
	 * @param context the context
	 */
	public TitleBar(Context context) {
		super(context);
		ininTitleBar(context);
		
	}
	
	/**
	 * Inin title bar.
	 *
	 * @param context the context
	 */
	public void ininTitleBar(Context context){
		
		mActivity  = (Activity)context;
		//水平排列
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setId(mAbTitleBarID);
		this.setClipToPadding(false);
		
		mInflater = LayoutInflater.from(context);
		
		layoutParamsFF = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParamsFW = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWF = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		layoutParamsWW = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWW.gravity = Gravity.CENTER_VERTICAL;
		
		titleTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1);
		titleTextLayoutParams.gravity = Gravity.CENTER_VERTICAL;
		rightViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rightViewLayoutParams.gravity = Gravity.CENTER_VERTICAL;
		leftViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		leftViewLayoutParams.gravity = Gravity.CENTER_VERTICAL;
		
		
		titleTextLayout = new LinearLayout(context);
		titleTextLayout.setOrientation(LinearLayout.VERTICAL);
		titleTextLayout.setGravity(Gravity.CENTER_VERTICAL);
		titleTextLayout.setPadding(0, 0, 0, 0);
		
		titleTextBtn = new Button(context);
		titleTextBtn.setTextColor(Color.WHITE);
		titleTextBtn.setTextSize(18);
		titleTextBtn.setPadding(5, 0, 5, 0);
		titleTextBtn.setGravity(Gravity.CENTER_VERTICAL);
		titleTextBtn.setBackgroundDrawable(null);
		titleTextBtn.setSingleLine();
		titleTextLayout.addView(titleTextBtn, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		
		titleSmallTextBtn = new Button(context);
		titleSmallTextBtn.setTextColor(Color.WHITE);
		titleSmallTextBtn.setTextSize(15);
		titleSmallTextBtn.setPadding(6, 0, 5, 0);
		titleSmallTextBtn.setGravity(Gravity.CENTER_VERTICAL);
		titleSmallTextBtn.setBackgroundDrawable(null);
		titleSmallTextBtn.setSingleLine();
		titleTextLayout.addView(titleSmallTextBtn, new LayoutParams(LayoutParams.WRAP_CONTENT, 0));
		
		logoView = new ImageView(context);
		logoView.setVisibility(View.GONE);
		logoLineView = new ImageView(context);
		logoLineView.setVisibility(View.GONE);
		logoView2 = new ImageView(context);
		logoView2.setVisibility(View.GONE);
		
		// 加左边的布局
		leftLayout = new LinearLayout(context);
		leftLayout.setOrientation(LinearLayout.HORIZONTAL);
		leftLayout.setGravity(Gravity.LEFT);
		leftLayout.setPadding(0, 0, 0, 0);
		leftLayout.setHorizontalGravity(Gravity.LEFT);
		leftLayout.addView(logoView, layoutParamsWW);
		leftLayout.addView(logoLineView, layoutParamsWW);
		leftLayout.addView(logoView2, layoutParamsWW);
		this.addView(leftLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		
		//加标题布局
		this.addView(titleTextLayout,titleTextLayoutParams);
		
		// 加右边的布局
		rightLayout = new LinearLayout(context);
		rightLayout.setOrientation(LinearLayout.HORIZONTAL);
		rightLayout.setGravity(Gravity.RIGHT);
		rightLayout.setPadding(0, 0, 0, 0);
		rightLayout.setHorizontalGravity(Gravity.RIGHT);
		rightLayout.setGravity(Gravity.CENTER_VERTICAL);
		rightLayout.setVisibility(View.GONE);
		this.addView(rightLayout,rightViewLayoutParams);
		
		logoView.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				mActivity.finish();
			}
		});
	}
	

	/**
	 * 描述：标题栏的背景图.
	 * @param res  背景图资源ID
	 */         
	public void setTitleBarBackground(int res) {
		this.setBackgroundResource(res);
	}
	
	/**
	 * 描述：设置标题背景.
	 * @param d  背景图
	 */
	public void setTitleBarBackgroundDrawable(Drawable d) {
		this.setBackgroundDrawable(d);
	}
	
	/**
	 * 描述：标题栏的背景图.
	 * @param color  背景颜色值
	 */
	public void setTitleBarBackgroundColor(int color) {
		this.setBackgroundColor(color);
	}
	
	/**
	 * 描述：标题文字的对齐,需要在setTitleBarGravity之后设置才生效.
	 * @param left the left
	 * @param top the top
	 * @param right the right
	 * @param bottom the bottom
	 */
	public void setTitleTextMargin(int left,int top,int right,int bottom) {
		titleTextLayoutParams.setMargins(left, top, right, bottom);
	}
	

	/**
	 * 描述：标题文字字号.
	 * @param titleTextSize  文字字号
	 */
	public void setTitleTextSize(int titleTextSize) {
		this.titleTextBtn.setTextSize(titleTextSize);
	}
	
	/**
	 * 描述：设置标题文字对齐方式
	 * 根据右边的具体情况判定方向：
	 * （1）中间靠近 Gravity.CENTER,Gravity.CENTER
	 * （2）左边居左 右边居右Gravity.LEFT,Gravity.RIGHT
	 * （3）左边居中，右边居右Gravity.CENTER,Gravity.RIGHT
	 * （4）左边居右，右边居右Gravity.RIGHT,Gravity.RIGHT
	 * 必须在addRightView(view)方法后设置
	 * @param gravity1  标题对齐方式
	 * @param gravity2  右边布局对齐方式
	 */
	public void setTitleBarGravity(int gravity1,int gravity2) {
		AbViewUtil.measureView(this.rightLayout);
		AbViewUtil.measureView(this.logoView);
		AbViewUtil.measureView(this.leftLayout);
		int leftWidth = this.leftLayout.getMeasuredWidth();
		int rightWidth = this.rightLayout.getMeasuredWidth();
		//if(D)Log.d(TAG, "测量布局的宽度："+leftWidth+","+rightWidth);
		this.titleTextLayoutParams.rightMargin = 0;
		this.titleTextLayoutParams.leftMargin = 0;
		//全部中间靠
		if((gravity1 == Gravity.CENTER_HORIZONTAL || gravity1 == Gravity.CENTER) ){
            if(leftWidth==0 && rightWidth==0){
            	this.titleTextLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			}else{
				if(gravity2 == Gravity.RIGHT){
					if(rightWidth==0){
					}else{
						this.titleTextBtn.setPadding(rightWidth/3*2, 0, 0, 0);
					}
					this.titleTextBtn.setGravity(Gravity.CENTER);
					this.rightLayout.setHorizontalGravity(Gravity.RIGHT);
				}if(gravity2 == Gravity.CENTER || gravity2 == Gravity.CENTER_HORIZONTAL){
					this.titleTextLayout.setGravity(Gravity.CENTER_HORIZONTAL);
					this.rightLayout.setHorizontalGravity(Gravity.LEFT);
					this.titleTextBtn.setGravity(Gravity.CENTER);
					int offset = leftWidth-rightWidth;
					if(offset>0){
						this.titleTextLayoutParams.rightMargin = offset;
					}else{
						this.titleTextLayoutParams.leftMargin = Math.abs(offset);
					}
				}
			}
		//左右
		}else if(gravity1 == Gravity.LEFT && gravity2 == Gravity.RIGHT){
			this.titleTextLayout.setGravity(Gravity.LEFT);
			this.rightLayout.setHorizontalGravity(Gravity.RIGHT);
		//全部右靠
		}else if(gravity1 == Gravity.RIGHT && gravity2 == Gravity.RIGHT){
			this.titleTextLayout.setGravity(Gravity.RIGHT);
			this.rightLayout.setHorizontalGravity(Gravity.RIGHT);
		}else if(gravity1 == Gravity.LEFT && gravity2 == Gravity.LEFT){
			this.titleTextLayout.setGravity(Gravity.LEFT);
			this.rightLayout.setHorizontalGravity(Gravity.LEFT);
		}
		
	}

	/**
	 * 描述：设置标题栏的高度
	 * @param height  the title bar height  dp
	 */
	public void setTitleBarHeight(int height){
		this.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
	}
	
	/**
	 * 描述：获取标题文本的Button.
	 * @return the title Button view
	 */
	public Button getTitleTextButton() {
		return titleTextBtn;
	}
	
	/**
	 * 描述：获取小标题文本的Button.
	 * @return the title Button view
	 */
	public Button getTitleSmallTextButton() {
		return titleSmallTextBtn;
	}
	
	/**
	 * 描述：获取标题Logo的View.
	 * @return the logo view
	 */
	public ImageView getLogoView() {
		return logoView;
	}
	
	/**
	 * 描述：获取标题Logo的View.
	 * @return the logo view
	 */
	public ImageView getLogoView2() {
		return logoView2;
	}
	
	/**
	 * 描述：设置标题字体粗体.
	 *
	 * @param bold the new title text bold
	 */
	public void setTitleTextBold(boolean bold){
		TextPaint paint = titleTextBtn.getPaint();
		if(bold){
			//粗体
			paint.setFakeBoldText(true); 
		}else{
			paint.setFakeBoldText(false); 
		}
		
	}
	
	/**
	 * 描述：设置标题背景.
	 *
	 * @param resId the new title text background resource
	 */
	public void setTitleTextBackgroundResource(int resId){
		titleTextBtn.setBackgroundResource(resId);
	}
	
	
	/**
	 * 描述：设置标题背景.
	 *
	 * @param drawable the new title text background drawable
	 */
	public void setTitleTextBackgroundDrawable(Drawable drawable){
		titleTextBtn.setBackgroundDrawable(drawable);
	}
	
	/**
     * 描述：设置标题文本.
     * @param text  文本
     */
	public void setTitleText(String text) {
		titleTextBtn.setText(text);
	}
	
	/**
     * 描述：设置标题文本.
     * @param resId  文本的资源ID
     */
	public void setTitleText(int resId) {
		titleTextBtn.setText(resId);
	}
	
	
	/**
     * 描述：设置小标题文本.
     * @param text  文本
     */
	public void setTitleSmallText(String text) {
		if(StringUtil.isEmpty(text)){
			LayoutParams titleSmallTextViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, 0);
			titleSmallTextBtn.setLayoutParams(titleSmallTextViewLayoutParams);
			titleSmallTextBtn.setText("");
		}else{
			LayoutParams titleSmallTextViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			titleSmallTextBtn.setLayoutParams(titleSmallTextViewLayoutParams);
			titleSmallTextBtn.setText(text);
		}
	}
	
	/**
     * 描述：设置标题文本.
     * @param resId  文本的资源ID
     */
	public void setTitleSmallText(int resId) {
		LayoutParams titleSmallTextViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleSmallTextBtn.setLayoutParams(titleSmallTextViewLayoutParams);
		titleSmallTextBtn.setText(resId);
	}
	
	/**
	 * 描述：设置Logo的背景图的大小.
	 * @param w  logo的宽度
	 * @param h log的高度
	 */
	public void setLogoSize(int w,int h) {
		logoView.setVisibility(View.VISIBLE);
		LayoutParams laParams=(LayoutParams)logoView.getLayoutParams();
		laParams.height=h;
		laParams.width= w;
		logoView.setLayoutParams(laParams);
	}
	
	public void setLogo(Drawable drawable) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setBackgroundDrawable(drawable);
	}
	
	/**
     * 描述：设置Logo的背景资源.
     * @param resId  Logo资源ID
     */
	public void setLogo(int resId) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setBackgroundResource(resId);
	}

	/**
	 * 设置Logo图片
	 * @param resId
	 */
	public void setLogoSrc(int resId) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		logoView.setImageResource(resId);
	}

	/**
	 * 设置设置Logo间距
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setLogoSrc(int left,int top,int right,int bottom) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setPadding(left, top, right, bottom);
	}
	
	/**
     * 描述：设置Logo的背景图.
     * @param drawable  Logo资源Drawable
     */
	public void setLogo2(Drawable drawable) {
		logoView2.setVisibility(View.VISIBLE);
		logoView2.setBackgroundDrawable(drawable);
	}
	
	/**
     * 描述：设置Logo的背景资源.
     * @param resId  Logo资源ID
     */
	public void setLogo2(int resId) {
		logoView2.setVisibility(View.VISIBLE);
		logoView2.setBackgroundResource(resId);
	}
	
	/**
     * 描述：设置Logo分隔线的背景资源.
     * @param resId  Logo资源ID
     */
	public void setLogoLine(int resId) {
		logoLineView.setVisibility(View.VISIBLE);
		logoLineView.setBackgroundResource(resId);
	}
	
	/**
     * 描述：设置Logo分隔线的背景图.
     * @param drawable  Logo资源Drawable
     */
	public void setLogoLine(Drawable drawable) {
		logoLineView.setVisibility(View.VISIBLE);
		logoLineView.setBackgroundDrawable(drawable);
	}
	
	
	/**
     * 描述：把指定的View填加到标题栏右边.
     * @param rightView  指定的View
     */
	public TitleBar addRightView(View rightView) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(rightView, layoutParamsFF);
		return this;
	}
	
	/**
     * 描述：把指定的View填加到标题栏左边.
     * @param leftView  指定的View
     */
	public TitleBar addLeftView(View leftView) {
		leftLayout.addView(leftView,new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		return this;
	}
	
	/**
     * 描述：把指定资源ID表示的View填加到标题栏左边.
     * @param resId  指定的View的资源ID
     */
	public TitleBar addLeftView(int resId) {
		leftLayout.removeAllViews();
		leftLayout.addView(mInflater.inflate(resId, null),new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		return this;
	}
	
	/**
     * 描述：把指定资源ID表示的View填加到标题栏右边.
     * @param resId  指定的View的资源ID
     */
	public TitleBar addRightView(int resId) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(mInflater.inflate(resId, null), layoutParamsFF);
		return this;
	}
	
	/**
     * 描述：把指定资源ID表示的View填加到标题栏.
     * @param resId  指定的View的资源ID
     */
	public TitleBar addTitleView(int resId) {
		titleTextLayout.removeAllViews();
		titleTextLayout.setVisibility(View.VISIBLE);
		titleTextLayout.addView(mInflater.inflate(resId, null), layoutParamsFF);
		return this;
	}

	/**
	 * 描述：把指定资源ID表示的View填加到标题栏.
	 */
	public TitleBar addTitleView(View view) {
		titleTextLayout.removeAllViews();
		titleTextLayout.setVisibility(View.VISIBLE);
		titleTextLayout.addView(view, layoutParamsFF);
		return this;
	}
	
	/**
     * 描述：清除标题栏右边的View.
     */
	public void clearRightView() {
		rightLayout.removeAllViews();
	}
	/**
     * 描述：清除标题栏左边的View.
     */
	public void clearLeftView() {
		leftLayout.removeAllViews();
	}
	
	/**
	 * 获取这个右边的布局,可用来设置位置.
	 *
	 * @return the right layout
	 */
	public LinearLayout getRightLayout() {
		return rightLayout;
	}
	/**
	 * 获取这个左边的布局,可用来设置位置.
	 *
	 * @return the left layout
	 */
	public LinearLayout getLeftLayout() {
		return leftLayout;
	}

	/**
	 * 描述：设置Logo按钮的点击事件.
	 * @param mOnClickListener  指定的返回事件
	 */
	public void setLogoOnClickListener(OnClickListener mOnClickListener) {
		 logoView.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * 描述：设置Logo按钮的点击事件.
	 * @param mOnClickListener  指定的返回事件
	 */
	public void setLogo2OnClickListener(OnClickListener mOnClickListener) {
		 logoView2.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * 描述：设置标题的点击事件.
	 * @param mOnClickListener  指定的返回事件
	 */
	public void setTitleTextOnClickListener(OnClickListener mOnClickListener) {
		titleTextBtn.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * 描述：下拉菜单的的实现方法.
	 *
	 * @param parent the parent
	 * @param view 要显示的View
	 * @param offsetMode 不填满的模式
	 */
	public void showWindow(View parent,View view,boolean offsetMode) {
		AbViewUtil.measureView(view);
		int popWidth = parent.getMeasuredWidth();
		int popMargin = (this.getMeasuredHeight()-parent.getMeasuredHeight())/2;
		if(view.getMeasuredWidth()>parent.getMeasuredWidth()){
			popWidth = view.getMeasuredWidth();
		}
		if(offsetMode){
			popupWindow = new PopupWindow(view, popWidth+10, LayoutParams.WRAP_CONTENT, true);
		}else{
			popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
		}
		
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.showAsDropDown(parent,0, popMargin+2);
	}
	
	/**
	 * 描述：隐藏Window.
	 */
	public void hideWindow() {
		if (popupWindow != null) {
			popupWindow.dismiss();
		}

	}
	
	/**
	 * 描述：设置标题下拉的View.
	 *
	 * @param view the new title text drop down
	 */
	public void setTitleTextDropDown(final View view){
		 if(view == null){
			   return;
		 }
		 setTitleTextOnClickListener(new OnClickListener() {
				
			 @Override
			 public void onClick(View v) {
				 showWindow(titleTextBtn,view,true);
			 }
		 });
	}

	/**
	 * 获取标题的全体布局.
	 *
	 * @return the title text layout
	 */
	public LinearLayout getTitleTextLayout() {
		return titleTextLayout;
	}
	
	/**
	 * 获取子布局显示宽度比例
	 * 默认为标题填充，右边靠右.
	 *
	 * @param left the new child view fill parent
	 */
	public void setChildViewFillParent(boolean left) {
		if(left){
			titleTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1);
			titleTextLayoutParams.gravity = Gravity.CENTER_VERTICAL;
			titleTextLayout.setLayoutParams(titleTextLayoutParams);
			
			rightViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rightViewLayoutParams.gravity = Gravity.CENTER_VERTICAL;
			rightLayout.setLayoutParams(rightViewLayoutParams);
			
		}else{
			titleTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			titleTextLayoutParams.gravity = Gravity.CENTER_VERTICAL;
			titleTextLayout.setLayoutParams(titleTextLayoutParams);
			
			rightViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1);
			rightViewLayoutParams.gravity = Gravity.CENTER_VERTICAL;
			rightLayout.setLayoutParams(rightViewLayoutParams);
		}
		
	}

	/**
	 * 修改标题栏距顶部的距离
	 * @param isFits true:标题栏沉侵时距离顶部标题栏高度 false:0
	 */
	public void setfitsWindowsBar(boolean isFits){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//在系统4.4上才存在侵入式
			RelativeLayout.LayoutParams  mTitleBarLayoutParams=(RelativeLayout.LayoutParams)this.getLayoutParams();
			this.setLayoutParams(mTitleBarLayoutParams);
			this.setPadding(0, isFits==true?getStatusBarHeight(mActivity):0, 0, 0);
		}
	}

	private   int getStatusBarHeight(Context context)
	{
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0)
		{
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
