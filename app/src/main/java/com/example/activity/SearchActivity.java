package com.example.activity;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.test.UiThreadTest;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.SearchAdapter;
import com.example.songofheaven.R;
import com.example.utill.Utills;

public class SearchActivity extends ActionBarActivity {
	
	public static final String EMAIL = "410775541@qq.com";
	ActionBar actionBar;
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		ListView searchList = (ListView) findViewById(R.id.result);
		final SearchAdapter searchAdapter = new SearchAdapter();

		ArrayList<String> allTitles = getIntent().getStringArrayListExtra(
				"allTitles");
		searchAdapter.setAllTitles(allTitles);
		searchList.setAdapter(searchAdapter);
		final TreeMap<String, String> titleToContentMap = Utills.getTitleToContentMap(MainActivity.sourceStrArray, this);
		
		searchList.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SearchActivity.this,ContentActivity.class);
				Bundle bundle = new Bundle();
				ArrayList<String> selectedTitles = searchAdapter.getSelectedTitles();
				String title = selectedTitles.get(position);
				bundle.putString("title", title);
				bundle.putString("content", titleToContentMap.get(title));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		editText = (EditText) findViewById(R.id.editText);
		editText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				String str = s.toString();
				str = str.toLowerCase();
				searchAdapter.getFilter().filter(str);
			}
		});

		editText.setText("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.searchmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_email) {
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
					new String[] { EMAIL });
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SongOfHeaven-"
					+ getResources().getString(R.string.customerback) + "("
					+ android.os.Build.MODEL + "-"
					+ android.os.Build.VERSION.SDK_INT + ")");
			emailIntent.setType("message/rfc822");
			startActivity(Intent.createChooser(emailIntent,
					getString(R.string.send_email_title)));
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clear(View view){
		editText.setText("");
	}

}
