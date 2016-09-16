package com.connectedio.ciodav001.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.connectedio.ciodav001.R;
import com.connectedio.ciodav001.model.News;
import com.connectedio.ciodav001.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsListAdapter extends BaseAdapter {
    Context context;
    ArrayList<News> newsList;
    LayoutInflater inflater;

    public NewsListAdapter(Context context, ArrayList<News> newsList) {
        this.context = context;
        this.newsList = newsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_news, parent, false);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.tv_title.setText(news.getTitle());
        holder.tv_description.setText(news.getDescription());
        Picasso.with(context)
                .load(news.getImage())
                .placeholder(R.drawable.connectedio_logo)
                .into(holder.iv_image);
        return convertView;
    }

    private class ViewHolder {
        TextView tv_title, tv_description;
        ImageView iv_image;
    }
}
