package com.connectedio.ciodav001.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import android.content.ContentValues;

public class Functions {
	public static String parameterEncode(ContentValues values) {
		String query = "";

		if (values != null) {
			for (String key : values.keySet()) {
				String value = values.getAsString(key);
				if (query != "")
					query += "&";
				query += key + "=" + URLEncoder.encode(value);
			}
		}
		return query;
	}

	public static String readStream(InputStream stream) {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(stream));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
