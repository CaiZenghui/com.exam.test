package com.exam.test.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class HttpUtil {
	public static String doGet(String url) {
		HttpGet httpRequest = new HttpGet(url);
		httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.setHeader("Accept-Encoding", "gzip");
		String strResult = "";

		try {
			HttpClient httpClient = getHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				InputStream gis = httpResponse.getEntity().getContent();
				Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
				if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					gis = new GZIPInputStream(new BufferedInputStream(gis));
				}
				strResult = inputStream2String(gis);
			}
		}
		catch (Exception e) {
			Log.d("Test", e.getMessage());
		}
		return strResult;
	}

	public static HttpClient getHttpClient() {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
		HttpConnectionParams.setSoTimeout(httpParameters, 5000);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(httpParameters, schReg);
		HttpClient client = new DefaultHttpClient(conMgr, httpParameters);
		return client;
	}

	private static String inputStream2String(InputStream is) {
		String str = "";
		ByteArrayOutputStream content = new ByteArrayOutputStream();
		byte[] b = new byte[4096];
		try {
			for (int n; (n = is.read(b)) != -1;) {
				content.write(b, 0, n);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			str = new String(content.toByteArray(), "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return str;
	}
}
