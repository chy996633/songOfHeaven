package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utill.Utills;

import java.util.TreeMap;

public class TitleAdapter extends BaseAdapter {

    TreeMap<String, String> titleToContentMap = new TreeMap<String, String>();


    public TitleAdapter(String sourceStr, Context context) {
        titleToContentMap = Utills.getTitleToContentMap(sourceStr, context);
    }

    public TreeMap getTitleToContentMap() {
        return titleToContentMap;
    }

    @Override
    public int getCount() {
        return titleToContentMap.size();
    }

    @Override
    public Object getItem(int position) {
        return titleToContentMap.keySet().toArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout line = new LinearLayout(parent.getContext());
        TextView text = new TextView(parent.getContext());
        text.setText((String) titleToContentMap.keySet().toArray()[position]);
        text.setTextSize(Utills.TITLE_TEXT_SIZE);
        line.addView(text);
        return line;
    }
}
