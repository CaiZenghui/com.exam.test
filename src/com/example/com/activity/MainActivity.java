package com.example.com.activity;

import com.example.com.exam.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
		findViewById(R.id.btn3).setOnClickListener(this);
		findViewById(R.id.btn4).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn1:
				startActivity(new Intent(this, WeatherActivity.class));
				break;

			case R.id.btn2:
				startActivity(new Intent(this, CustomViewActivity.class));
				break;

			case R.id.btn3:
				startActivity(new Intent(this, ImageActivity.class));
				break;

			case R.id.btn4:

				break;
		}

	}

}
