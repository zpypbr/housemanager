package com.geek.house.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class Util {
	private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");

	public static String createUniqueId(String name) {
		long l1 = System.currentTimeMillis();
		return name + "-" + mFormat.format(new Date(l1));
	}

	public static String FormatDate(Date paramDate) {
		return new SimpleDateFormat("yyyy-MM-dd").format(paramDate);
	}
}
