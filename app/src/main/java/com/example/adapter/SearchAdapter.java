package com.example.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


import com.example.utill.Utills;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {

    private Filter filter;
    private ArrayList<String> allTitles = new ArrayList<String>();
    private ArrayList<String> selectedTitles = new ArrayList<String>();
    private TreeMap<String, String> titleToContentMap;

    public ArrayList<String> getSelectedTitles() {
        return selectedTitles;
    }

    public void setAllTitles(ArrayList<String> allTitles) {
        this.allTitles = allTitles;
        this.selectedTitles = allTitles;
    }

    public void setTitleToContentMap(TreeMap<String, String> titleToContentMap) {
        this.titleToContentMap = titleToContentMap;
    }

    @Override
    public int getCount() {
        return selectedTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return selectedTitles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return selectedTitles.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout title = new LinearLayout(parent.getContext());
        TextView titleView = new TextView(parent.getContext());
        titleView.setText(selectedTitles.get(position));
        titleView.setTextSize(Utills.TITLE_TEXT_SIZE);
        title.addView(titleView);
        return title;
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new SearchFilter(titleToContentMap);
        }
        return filter;
    }

    private class SearchFilter extends Filter {

        private TreeMap<String, String> titleToContentMap;

        public SearchFilter(TreeMap<String, String> titleToContentMap) {

            this.titleToContentMap = titleToContentMap;
        }

        protected FilterResults performFiltering(CharSequence keywords) {

            FilterResults results = new FilterResults();
            if (keywords == null || keywords.length() == 0) {
                results.values = allTitles;
                results.count = allTitles.size();
            } else {
                ArrayList<String> mList = new ArrayList<String>();
                Iterator<String> itr = titleToContentMap.keySet().iterator();
                while (itr.hasNext()) {
                    String next = itr.next();
                    //add both title and content that contains key words to mList
                    if (next.contains(keywords) || titleToContentMap.get(next).contains(keywords)) {
                        mList.add(next);
                    }
                }

                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            selectedTitles = (ArrayList<String>) results.values;
            System.out.println("selected:"+ selectedTitles.toString());
            notifyDataSetChanged();
        }
    }

}
