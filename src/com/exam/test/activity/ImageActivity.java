package com.exam.test.activity;

import java.net.URL;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.exam.test.view.CustomImageView;
import com.example.com.exam.test.R;

public class ImageActivity extends Activity {

	private CustomImageView customImageView;
	private String[] urls = { "http://llss.qiniudn.com/forum/image/525d1960c008906923000001_1397820588.jpg",
			"http://llss.qiniudn.com/forum/image/e8275adbeedc48fe9c13cd0efacbabdd_1397877461243.jpg",
			"http://llss.qiniudn.com/uploads/forum/topic/attached_img/5350db2ffcfff258b500dcb2/_____2014-04-18___3.52.33.png" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		customImageView = (CustomImageView) findViewById(R.id.customImageView);
		getBitmap(urls[(int) (Math.random() * 3)]);
	}

	public void getBitmap(final String path) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					URL url = new URL(path);
					final Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
					customImageView.post(new Runnable() {
						public void run() {
							customImageView.setImageBitmap(bitmap);
						}
					});

				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
