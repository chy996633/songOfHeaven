package com.example.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Utills {

	public static final String TITLE_REG = ".*[0-9][0-9][0-9].*";
	private static final int DEFAULT_WIDTH = 1920;
	public static final int TITLE_TEXT_SIZE = 22;
	public static final int CONTENT_TEXT_SIZE = 20;

	public static ArrayList<String> getAllTitles(String[] paths, Context context)
			throws IOException {
		BufferedReader bufferedReader = null;
		ArrayList<String> titles = new ArrayList<String>();
		for (String path : paths) {
			InputStream inputStream = context.getResources().getAssets()
					.open(path);
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream, "GBK"));
				String s;
				while ((s = bufferedReader.readLine()) != null) {
					if (s.matches(TITLE_REG)) {
						titles.add(s);
					}
				}
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		}
		return titles;
	}

	public static ArrayList<String> getAllTitles(String path, Context context) {
		BufferedReader bufferedReader = null;
		ArrayList<String> titles = new ArrayList<String>();
		try {
			InputStream inputStream = context.getResources().getAssets()
					.open(path);
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, "GBK"));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				if (s.matches(TITLE_REG)) {
					titles.add(s);
				}
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return titles;
	}

	public static TreeMap<String, String> getTitleToContentMap(String path,
			Context context) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		StringBuffer content = new StringBuffer();
		StringBuffer title = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = context.getResources().getAssets()
					.open(path);
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, "GBK"));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				if (s.matches(TITLE_REG)) {
					map.put(title.toString(), content.toString());
					title = new StringBuffer();
					content = new StringBuffer();
					title.append(s);
				} else {
					content.append(s + "\n");
				}
			}
			map.put(title.toString(), content.toString());
			map.remove("");

			if (bufferedReader != null) {
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static TreeMap<String, String> getTitleToContentMap(String[] paths,
			Context context) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		StringBuffer content = new StringBuffer();
		StringBuffer title = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			for (String path : paths) {
				InputStream inputStream = context.getResources().getAssets()
						.open(path);
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream, "GBK"));
				String s;
				while ((s = bufferedReader.readLine()) != null) {
					if (s.matches(TITLE_REG)) {
						map.put(title.toString(), content.toString());
						title = new StringBuffer();
						content = new StringBuffer();
						title.append(s);
					} else {
						content.append(s + "\n");
					}
				}
				map.put(title.toString(), content.toString());
				map.remove("");

				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static float calculateScreenScale(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		return point.x / DEFAULT_WIDTH;
	}

	public static float calculateScreenDensity(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		((WindowManager) context.getSystemService(context.WINDOW_SERVICE))
				.getDefaultDisplay().getMetrics(metric);
		System.out.println("density:" + metric.density);
		return metric.density;
	}

}
