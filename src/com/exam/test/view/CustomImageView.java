package com.exam.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

public class CustomImageView extends ImageView {

	private int need_height;
	private int need_width;

	public CustomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		need_width = width - (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(need_width, need_height);
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		need_height = (int) (height / Float.parseFloat(width + "") * need_width);
		super.setImageBitmap(bm);
	}
}
