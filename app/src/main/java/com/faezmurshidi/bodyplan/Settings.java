package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Settings extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_setting, null, false);
        mDrawerLayout.addView(contentView, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seventh, menu);
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

    public void about(View view) {
        Intent intent = null;
        intent = new Intent(this, About.class);
        startActivity(intent);


    }

    public void updateUser(View view) {

        startActivity(new Intent(this, UserProfile.class));

    }

    public void gotohealthvault(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://https://www.healthvault.com/my/en"));
        startActivity(i);


    }

    public void share(View view) {

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Get SmartCare now! your secure mobile personal health record solution.\n");

        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.faezmurshidi.bodyalarm");

        startActivity(Intent.createChooser(share, "Share link!"));


    }
}
