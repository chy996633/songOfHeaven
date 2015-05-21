package com.example.activity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.MainAdapter;
import com.example.songofheaven.R;
import com.example.utill.Utills;

public class MainActivity extends ActionBarActivity {

	public static final String EMAIL = "410775541@qq.com";
	public final static String[] sourceStrArray = new String[] { "Psalms.txt",
			"Hymns.txt", "Spiritual.txt", "Local.txt" };
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getActionBar();
		ListView mainList = (ListView) findViewById(R.id.mainList);
		MainAdapter mainAdapter = new MainAdapter();
		mainList.setAdapter(mainAdapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putString("source", sourceStrArray[position]);
				bundle.putString("title", MainAdapter.mainPage[position]);
				Intent intent = new Intent(MainActivity.this,
						TitleActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_search) {
			Bundle bundle = new Bundle();
			ArrayList<String> allTitles = new ArrayList<String>();
			try {
				allTitles = Utills.getAllTitles(sourceStrArray, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			bundle.putStringArrayList("allTitles", allTitles);
			Intent intent = new Intent(MainActivity.this, SearchActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
