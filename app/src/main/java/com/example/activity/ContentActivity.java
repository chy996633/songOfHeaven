package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.songofheaven.R;
import com.example.utill.Utills;

public class ContentActivity extends Activity{
	public static final String EMAIL = "410775541@qq.com";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		
		String titleStr = getIntent().getStringExtra("title");
		String contentStr = getIntent().getStringExtra("content");
		TextView titleView = (TextView)findViewById(R.id.title);
        titleView.setTextSize(Utills.TITLE_TEXT_SIZE);
		titleView.setText(titleStr);
		TextView contentView = (TextView)findViewById(R.id.content);
        contentView.setTextSize(Utills.CONTENT_TEXT_SIZE);
		contentView.setText(contentStr);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.contentmenu, menu);
		return true;
	}
	
}
