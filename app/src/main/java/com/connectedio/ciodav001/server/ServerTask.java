package com.connectedio.ciodav001.server;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerTask extends AsyncTask<String, Void, Integer> {

    public static int CONNECTION_TIMEOUT =20000;
    public static int SOCKET_TIMEOUT = 20000;
    public OnServerTaskListener listener;
    ContentValues values = null;
    int request;
    String method;
    String url;
    long id;
    ProgressDialog progressDialog;
    Context context;
    String response;
    int responseCode;

    public ServerTask(String url, String method, ContentValues values) {
        this.url = url;
        this.method = method;
        this.values = values;
    }

    public ServerTask(Context context, String url, String method, ContentValues values, int request) {
        this.url = url;
        this.method = method;
        this.values = values;
        this.request = request;
        this.context = context;
    }

    public void start() {
        this.execute(url);
    }

    public void stop() {
        this.cancel(true);
    }

    public void setServerTaskListner(OnServerTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        InputStream errorStream = null;
        try {
            String urlString = params[0].toString();
            if (method == "GET" && values != null) {
                urlString += "?" + Functions.parameterEncode(values);
            }
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");
            conn.setReadTimeout(SOCKET_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            if (method == "POST" || method == "PUT") {
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(Functions.parameterEncode(values));
                writer.flush();
                writer.close();
                os.close();
            }
            conn.connect();


            responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                is = conn.getInputStream();
                response = Functions.readStream(is);
                if (is != null) {
                    is.close();
                }
                conn.disconnect();
            } else {
                errorStream = conn.getErrorStream();
                response = Functions.readStream(errorStream);
                if (errorStream != null) {
                    errorStream.close();
                }
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            response = "Malformed URL Exception";
        } catch (IOException e) {
            e.printStackTrace();
            response = "Connection failed";
        }
        return responseCode;
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        super.onPostExecute(responseCode);
        dismissProgressDialog();
        if (responseCode == 200) {
            this.listener.onServerTaskCompletion(response, request, id);
        } else {
            this.listener.onServerTaskFailed(responseCode, response, request);

        }
    }

    public void showProgressDialog(Context c) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(c);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            if (!progressDialog.isShowing())
                progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
