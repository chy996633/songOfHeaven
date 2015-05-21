package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.TitleAdapter;
import com.example.songofheaven.R;
import com.example.utill.Utills;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

public class TitleActivity extends Activity {
    public static final String EMAIL = "410775541@qq.com";
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        String title = getIntent().getStringExtra("title");
        setTitle(title);
        String sourceStr = getIntent().getStringExtra("source");
        System.out.println("sourceStr:" + sourceStr + " title:" + title);
        ListView titleList = (ListView) findViewById(R.id.title);
        TitleAdapter adapter = new TitleAdapter(sourceStr, this);
        titleList.setAdapter(adapter);
        final TreeMap<String, String> titleToContentMap = Utills
                .getTitleToContentMap(sourceStr, this);
        titleList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(TitleActivity.this,
                        ContentActivity.class);
                Bundle bundle = new Bundle();
                String title = (String) titleToContentMap.keySet().toArray()[position];
                bundle.putString("title", title);
                bundle.putString("content", titleToContentMap.get(title));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.titlemenu, menu);
        return true;
    }



}
