package com.connectedio.ciodav001.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.connectedio.ciodav001.R;
import com.connectedio.ciodav001.model.Result;

import java.util.ArrayList;

public class ResultListAdapter extends BaseAdapter {
	Context context;
	ArrayList<Result> results;
	LayoutInflater inflater;

	public ResultListAdapter(Context context, ArrayList<Result> results) {
		this.context = context;
		this.results = results;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return results.size();
	}

	@Override
	public Result getItem(int position) {
		return results.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Result results = getItem(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.row_result, parent, false);
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_key);
			holder.tv_text = (TextView) convertView.findViewById(R.id.tv_value);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tv_title.setText(results.getKey());
		holder.tv_text.setText(results.getValue());
		return convertView;
	}

	private class ViewHolder {
		TextView tv_title, tv_text;
	}
}
