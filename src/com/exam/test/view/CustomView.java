package com.exam.test.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 只为做出动画效果，很多地方做了简化处理；
 * @author YeLuo-PC
 *
 */
public class CustomView extends View {

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bgPaint.setColor(Color.GREEN);
		txtPaint.setColor(Color.RED);
		txtPaint.setTextSize(150);
		txtPaint.setTextAlign(Align.CENTER);
	}

	private Paint bgPaint = new Paint();
	private Paint txtPaint = new Paint();
	private float circle;
	private String text;

	public float getCircle() {
		return circle;
	}

	public void setCircle(float circle) {
		this.circle = circle;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (circle == 0) {
			circle = getWidth() / 2;
		}
		if (TextUtils.isEmpty(text)) {
			text = "3";
		}
		canvas.translate(getWidth() / 2, getHeight() / 2);
		canvas.drawColor(Color.WHITE);
		canvas.drawCircle(0, 0, circle, bgPaint);
		canvas.drawText(text, 0, 150/4, txtPaint);
	}

	public void setText(String txt) {
		if (!"0".equals(txt)) {
			text = txt;
			ObjectAnimator animator = ObjectAnimator.ofObject(this, "circle", new FloatEvaluator(), getWidth() / 2 * 0.6, getWidth() / 2,
					getWidth() / 2 * 0.6);
			animator.setDuration(500);
			animator.start();
		}
		else {
			ObjectAnimator animator = ObjectAnimator.ofObject(this, "circle", new FloatEvaluator(), getWidth() / 2 * 0.6, getWidth() / 2);
			ObjectAnimator alphaAnimator = ObjectAnimator.ofObject(this, "alpha", new FloatEvaluator(), 1, 0);
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.playTogether(animator,alphaAnimator);
			animatorSet.setDuration(500);
			animatorSet.addListener(new AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animation) {
				}

				@Override
				public void onAnimationRepeat(Animator animation) {
				}

				@Override
				public void onAnimationEnd(Animator animation) {
					((ViewGroup) getParent()).removeAllViews();
				}

				@Override
				public void onAnimationCancel(Animator animation) {
				}
			});
			animatorSet.start();
		}
	}
}
