package com.faezmurshidi.bodyplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faez on 07/10/2015.
 */
public class UserProfile extends MainActivity {

    SharedPreferences.Editor edit;
    EditText name,age,weight,height;
    Spinner sex,blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.profile, null, false);
        mDrawerLayout.addView(contentView, 0);

        SharedPreferences preferences = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        edit = preferences.edit();
        edit.putBoolean("isLoggedIn", true);


        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);

        sex = (Spinner)findViewById(R.id.sex);


        List<String> sexchoose = new ArrayList<>();
        sexchoose.add("Male");
        sexchoose.add("Feemale");



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexchoose);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(dataAdapter);

















    }


    

    public void commit(View view) {

        Toast.makeText(getApplicationContext(),"Updating user profile",Toast.LENGTH_LONG).show();

        edit.putString("name", name.getText().toString());
        edit.putString("age",age.getText().toString());
        edit.putString("weight",weight.getText().toString());
        edit.putString("height",height.getText().toString());

        edit.putString("sex", sex.getSelectedItem().toString());


        edit.commit();

        Toast.makeText(getApplicationContext(),"User profile update",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainTab.class));



    }
}
