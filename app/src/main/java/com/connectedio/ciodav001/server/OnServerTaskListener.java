package com.connectedio.ciodav001.server;

public interface OnServerTaskListener {
	void onServerTaskCompletion(String result, int request, long id);
	void onServerTaskFailed(int responseCode, String error, int request);
}

