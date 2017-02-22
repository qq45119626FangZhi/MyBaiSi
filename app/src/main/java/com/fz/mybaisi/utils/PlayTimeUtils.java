package com.fz.mybaisi.utils;

import android.content.Context;
import android.net.TrafficStats;

import java.util.Formatter;
import java.util.Locale;

public class PlayTimeUtils {

	private StringBuilder mFormatBuilder;
	private Formatter mFormatter;

	public PlayTimeUtils() {
		// 转换成字符串的时间
		mFormatBuilder = new StringBuilder();
		mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

	}

	/**
	 * 把毫秒转换成：1:20:30这里形式
	 * @param timeMs
	 * @return
	 */
	public String stringForTime(int timeMs) {
		int totalSeconds = timeMs / 1000;
		int seconds = totalSeconds % 60;

		int minutes = (totalSeconds / 60) % 60;

		int hours = totalSeconds / 3600;

		mFormatBuilder.setLength(0);
		if (hours > 0) {
			return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds)
					.toString();
		} else {
			return mFormatter.format("%02d:%02d", minutes, seconds).toString();
		}
	}

	/**
	 * 判断是否是网络播放地址
	 *
	 * @param uri
	 * @return
	 */
	public boolean isNetUri(String uri) {
		boolean isNetUri = false;
		if (uri != null) {
			if (uri.toLowerCase().startsWith("http") || uri.toLowerCase().startsWith("mms") || uri.toLowerCase().startsWith("rtsp")) {
				isNetUri = true;
			}
		}
		return isNetUri;
	}

	private long lastTotalRxBytes = 0;
	private long lastTimeStamp = 0;
	/**
	 * 得到网速
	 * 注意：每隔一段时间去调用，每秒种
	 * @param context
	 * @return
	 */
	public String getNetSpeed(Context context) {

		long nowTotalRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
		;
		long nowTimeStamp = System.currentTimeMillis();
		long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换

		lastTimeStamp = nowTimeStamp;
		lastTotalRxBytes = nowTotalRxBytes;

		String speedStr = String.valueOf(speed) + " kb/s";

		return speedStr;

	}

}
