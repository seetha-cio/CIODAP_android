package com.connectedio.ciodav001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.connectedio.ciodav001.adapter.NewsListAdapter;
import com.connectedio.ciodav001.model.News;
import com.connectedio.ciodav001.server.OnServerTaskListener;
import com.connectedio.ciodav001.server.ServerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NewsActivity extends AppCompatActivity implements OnServerTaskListener {
    ListView lv_news;
    NewsListAdapter adapter;
    ArrayList<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv_news = (ListView) findViewById(R.id.lv_news);
        newsList = new ArrayList<>();
        adapter = new NewsListAdapter(this, newsList);
        lv_news.setAdapter(adapter);
        ServerTask serverTask = new ServerTask("http://beta.connectedio.com/CIODA/news.txt", "GET", null);
        serverTask.setServerTaskListner(this);
        serverTask.start();
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

    }

    @Override
    public void onServerTaskCompletion(String result, int request, long id) {
        try {
            JSONArray resultArray = new JSONArray(result);
            int count = resultArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject obj = resultArray.getJSONObject(i);
                String title = obj.getString("title");
                String description = obj.getString("description");
                String author = obj.getString("author");
                int newsId = obj.getInt("id");
                String image = obj.getString("image");
                String video = obj.getString("video");
                String date = obj.getString("date");
                newsList.add(new News(title, description, author, date, image, video, newsId));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onServerTaskFailed(int responseCode, String error, int request) {

    }
}
