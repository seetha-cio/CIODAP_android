package com.connectedio.ciodav001;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.connectedio.ciodav001.server.OnServerTaskListener;
import com.connectedio.ciodav001.server.ServerTask;
import com.connectedio.ciodav001.utils.Constants;
import com.connectedio.ciodav001.utils.MyPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity implements OnClickListener, OnServerTaskListener {
    EditText et_email, et_password;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        /*<dimen name="activity_horizontal_margin">0dp</dimen>
        <dimen name="activity_vertical_margin">0dp</dimen>*/
    }

    @Override
    public void onClick(View v) {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        if (email == null || email.length() <= 0) {
            et_email.setError("Email cannot be empty");
            return;
        }

    callLogin(email,password);


    }
    private void callLogin(String email,String password){
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("pass",password);
       ServerTask serverTask = new ServerTask(LoginActivity.this, Constants.URL_LOGIN_SUCCESS, "POST", values, 0);
        serverTask.setServerTaskListner(this);
        serverTask.start();
    }

    @Override
    public void onServerTaskCompletion(String result, int request, long id) {
        try {
            JSONObject json=new JSONObject(result);
            if(json.getBoolean("success")){
                String userId=json.getString("userId");

               // MyPreferences.getInstance(LoginActivity.this).clear();
                MyPreferences.getInstance(LoginActivity.this).setIsLogin(true);
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }else{
                String error=json.getString("message");
                Toast.makeText(LoginActivity.this,error,Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServerTaskFailed(int responseCode, String error, int request) {
        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_LONG).show();

    }
}

