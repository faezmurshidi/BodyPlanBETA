package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class Motivation extends MainActivity {

    ImageView ivIcon;
    TextView tvItemName;
    Button btnShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.motivation, null, false);
        mDrawerLayout.addView(contentView, 0);


        //setTitle(listArray[position]);

        btnShare = (Button) findViewById(R.id.buttonShare);
        final String[] tipstitle = getResources().getStringArray(R.array.inspiration);
        Random ran = new Random();
        int maxIndex = tipstitle.length;
        final int generatedIndex = ran.nextInt(maxIndex);






        tvItemName = (TextView) findViewById(R.id.textView8);
        tvItemName.setText(tipstitle[generatedIndex]);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_TEXT, tipstitle[generatedIndex]+"\n");

                share.putExtra(Intent.EXTRA_TEXT, "Get BodyPlan now - your complete body app.");

                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




}