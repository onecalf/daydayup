package com.onecalf.hard;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DragViewActivity extends Activity {
    ListView mListView;
    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drag_view_layout);
        mListView = (ListView) findViewById(R.id.list_view);

        for (int i = 0; i < 50; i++) {
            names.add("张三 " + i);
        }


        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return names.size();
            }

            @Override
            public Object getItem(int position) {
                return names.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) LayoutInflater.from(DragViewActivity.this).inflate(R.layout.item_name, parent, false);
                item.setText(names.get(position));
                return item;
            }
        });

    }


}










