package com.faezmurshidi.bodyplan;

/**
 * Created by faez on 12/14/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;




public class workoutlist extends MainActivity {

    ListView lvItems;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.workoutlist, null, false);
        mDrawerLayout.addView(contentView, 0);


        String[] values = getResources().getStringArray(R.array.workoutTitle);

        lvItems = (ListView) findViewById(R.id.rowText);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        lvItems.setAdapter(itemsAdapter);


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(workoutlist.this, Workout.class);

                startActivity(in);



            }
        });
    }


    }
