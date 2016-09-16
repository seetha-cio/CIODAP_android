package com.connectedio.ciodav001.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetStatus {

	public static boolean isConnectedToInternet(Context ctx) {
		boolean connected = false;
		try {
			ConnectivityManager cManager = (ConnectivityManager) ctx
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = cManager.getActiveNetworkInfo();
			connected = networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return connected;
	}
}
