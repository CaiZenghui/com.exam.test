package com.exam.test.activity;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.exam.test.model.WeatherInfo;
import com.exam.test.model.WeatherInfo.Forecast;
import com.exam.test.model.WeatherInfo.Realtime;
import com.exam.test.utils.HttpUtil;
import com.example.com.exam.test.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WeatherActivity extends Activity {

	private TextView tv_city;
	private TextView tv_weather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);

		initView();
		getDataFromServer();
	}

	private void getDataFromServer() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = HttpUtil
						.doGet("http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101010100&imei=529e2dd3d767bdd3595eec30dd481050&device=pisces&miuiVersion=JXCCNBD20.0&modDevice=&source=miuiWeatherApp");
				try {
					Gson gson = new Gson();
					Type type = new TypeToken<WeatherInfo>() {
					}.getType();
					final WeatherInfo info = gson.fromJson(result, type);
					tv_city.post(new Runnable() {
						@Override
						public void run() {
							tv_city.setText("forecast. city:" + info.getForecast().getCity());
							tv_weather.setText("realtime. weather:" + info.getRealtime().getWeather());
						}
					});

					saveCacheObject(info, "weather_info");
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void initView() {
		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_weather = (TextView) findViewById(R.id.tv_weather);
	}

	public boolean saveCacheObject(Serializable object, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = openFileOutput(fileName, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.flush();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				oos.close();
			}
			catch (Exception e) {
			}
			try {
				fos.close();
			}
			catch (Exception e) {
			}
		}
	}

}
