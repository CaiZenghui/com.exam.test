package com.example.test.model;

import java.io.Serializable;

/**
 * 仅取json中部分字段做示例；
 */
public class WeatherInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Forecast forecast;
	private Realtime realtime;

	public Realtime getRealtime() {
		return realtime;
	}

	public void setRealtime(Realtime realtime) {
		this.realtime = realtime;
	}

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}

	public class Forecast implements Serializable {
		private static final long serialVersionUID = 1L;
		private String city;
		private String city_en;
		private String cityid;
		private String date_y;
		private String fchh;
		private String fl1;
		private String img_title1;
		private String temp1;
		private String weather1;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCity_en() {
			return city_en;
		}

		public void setCity_en(String city_en) {
			this.city_en = city_en;
		}

		public String getCityid() {
			return cityid;
		}

		public void setCityid(String cityid) {
			this.cityid = cityid;
		}

		public String getDate_y() {
			return date_y;
		}

		public void setDate_y(String date_y) {
			this.date_y = date_y;
		}

		public String getFchh() {
			return fchh;
		}

		public void setFchh(String fchh) {
			this.fchh = fchh;
		}

		public String getFl1() {
			return fl1;
		}

		public void setFl1(String fl1) {
			this.fl1 = fl1;
		}

		public String getImg_title1() {
			return img_title1;
		}

		public void setImg_title1(String img_title1) {
			this.img_title1 = img_title1;
		}

		public String getTemp1() {
			return temp1;
		}

		public void setTemp1(String temp1) {
			this.temp1 = temp1;
		}

		public String getWeather1() {
			return weather1;
		}

		public void setWeather1(String weather1) {
			this.weather1 = weather1;
		}
	}

	public class Realtime implements Serializable {
		private static final long serialVersionUID = 1L;
		private String weather;
		private String time;
		private String temp;
		private String radar;

		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getRadar() {
			return radar;
		}

		public void setRadar(String radar) {
			this.radar = radar;
		}
	}
}
