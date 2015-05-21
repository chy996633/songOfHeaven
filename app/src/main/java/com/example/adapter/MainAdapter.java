package com.example.adapter;

import com.example.utill.Utills;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter{

    public final static String[] mainPage = new String[]{"诗章(102-112)","颂词(301-364)","灵歌(501-526)","特色歌曲(901-927)"};

    @Override
    public int getCount() {
        return mainPage.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout line = new LinearLayout(parent.getContext());
        line.setGravity(Gravity.CENTER);
        TextView text = new TextView(parent.getContext());
        text.setText(mainPage[position]);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(Utills.TITLE_TEXT_SIZE);
        line.addView(text);
        return line;
    }




}
