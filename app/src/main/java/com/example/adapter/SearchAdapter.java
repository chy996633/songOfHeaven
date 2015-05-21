package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;





import com.example.utill.Utills;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter{

	private Filter filter;
	private ArrayList<String> allTitles = new ArrayList<String>();
	private ArrayList<String> selectedTitles = new ArrayList<String>();
	
	public ArrayList<String> getSelectedTitles() {
		return selectedTitles;
	}

	public void setAllTitles(ArrayList<String> allTitles){
		this.allTitles = allTitles;
		this.selectedTitles = allTitles;
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
			filter = new SearchFilter(allTitles);
		}
		return filter;
	}
	
	private class SearchFilter extends Filter {
		
		private ArrayList<String> titles;
		public SearchFilter(ArrayList<String> titles) {
			
			this.titles = titles;
		}
		protected FilterResults performFiltering(CharSequence constraint) {
			
			FilterResults results = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				results.values = titles;
				results.count = titles.size();
			} else {
				ArrayList<String> mList = new ArrayList<String>();
				for (String data : titles) {
					if (data.contains(constraint)) {
						mList.add(data);
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
			notifyDataSetChanged();
		}
	}

}
