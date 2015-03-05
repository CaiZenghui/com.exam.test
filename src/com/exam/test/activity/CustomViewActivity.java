package com.exam.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.exam.test.view.CustomView;
import com.example.com.exam.test.R;

public class CustomViewActivity extends Activity {

	private CustomView customView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view);

		customView = (CustomView) findViewById(R.id.customView);
		startAnimation();
	}

	private void startAnimation() {
		customView.post(new Runnable() {
			@Override
			public void run() {
				new Thread(new Runnable() {
					private int num=3;
					public void run() {
						while(num>0){
							try {
								Thread.sleep(1000);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
							num--;
							customView.post(new Runnable() {
								
								@Override
								public void run() {
									customView.setText(num+"");
								}
							});
						}
					}
					
				}).start();
			}
		});

	}
}
